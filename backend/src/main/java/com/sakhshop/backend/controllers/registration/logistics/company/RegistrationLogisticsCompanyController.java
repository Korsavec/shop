package com.sakhshop.backend.controllers.registration.logistics.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sakhshop.backend.email.SendEmail;
import com.sakhshop.backend.enam.RoleEnum;
import com.sakhshop.backend.models.activation.NotActivatedLogisticsCompany;
import com.sakhshop.backend.models.logistics.company.LogisticsCompany;
import com.sakhshop.backend.models.payload.request.logistics.company.RegistrationLogisticsCompanyRequest;
import com.sakhshop.backend.models.payload.response.MessageResponse;
import com.sakhshop.backend.models.role.RoleLogisticsCompany;
import com.sakhshop.backend.models.token.Token;
import com.sakhshop.backend.service.cache.LoginAttemptService;
import com.sakhshop.backend.service.jpa.ServiceJpa;
import com.sakhshop.backend.validation.ValidationRegExp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static com.sakhshop.backend._helper.Handler.generateFileName;
import static com.sakhshop.backend._helper.Handler.getExtension;
import static com.sakhshop.backend._helper.characters.GetCharacterFromStrings.charList;
import static com.sakhshop.backend.config.Constants.*;
import static com.sakhshop.backend.service.compression.CompressionImage.compressionAndConversion;
import static java.nio.file.Paths.get;

@RestController
@RequestMapping("/api/auth")
public class RegistrationLogisticsCompanyController {


    private final
    ValidationRegExp validationRegExp;

    private final
    LoginAttemptService limitLogin;

    private final
    PasswordEncoder encoder;

    private final
    ServiceJpa serviceJpa;

    private final
    SendEmail sendEmail;

    public RegistrationLogisticsCompanyController(ValidationRegExp validationRegExp, ServiceJpa serviceJpa, PasswordEncoder encoder, SendEmail sendEmail, LoginAttemptService limitLogin) {
        this.validationRegExp = validationRegExp;
        this.serviceJpa = serviceJpa;
        this.encoder = encoder;
        this.sendEmail = sendEmail;
        this.limitLogin = limitLogin;
    }



    @PostMapping(value = "/registrationLogisticsCompany", consumes = "multipart/form-data", produces = "application/json")
    public ResponseEntity<MessageResponse> registerLogisticsCompany(@RequestParam(value = "image") MultipartFile multipartFile,
                                                          @RequestParam(value = "registrationLogisticsCompany") String registrationLogisticsCompanyRequest,
                                                          HttpServletRequest request) throws IOException {



        // Конвертируем LogisticsCompany в объект LogisticsCompanyRequest
        ObjectMapper objectMapper = new ObjectMapper();


        RegistrationLogisticsCompanyRequest value = objectMapper.readValue(registrationLogisticsCompanyRequest, RegistrationLogisticsCompanyRequest.class);



        // Получаем расширение файла на основе MimeType
        String extension = getExtension(Objects.requireNonNull(multipartFile.getContentType()));



        if (Objects.equals(extension, "")) {
            return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), STATIC_OK));
        }

        // Генерируем случайное временное имя файла
        String temporaryFileTame = UUID.randomUUID().toString();

        // Создаём путь к файлу у которого временное имя
        String pathFileTmp = ENVIRONMENT_PATH_TMP_PASSPORT_COMPANY + SLASH + temporaryFileTame + DOT + extension;

        // Сохраняем этот файл с временным именем
        Path sourcePathFileTmp = get(pathFileTmp);
        multipartFile.transferTo(sourcePathFileTmp);

        // Получаем размер файла перед сжатием
        File fileBeforeCompressTmp = new File(pathFileTmp);
        long fileBeforeCompressTmp1 = fileBeforeCompressTmp.length();

        // Сжимаем изображение + конвертируем в jpg если это png + очищаем метаданные
        // После конвертации получаются два файла, один оригинал png и второй на выходе jpg, при условии, что исходный был png
        boolean compressed = compressionAndConversion(ENVIRONMENT_PATH_TMP_PASSPORT_COMPANY, temporaryFileTame, extension);

        if (!compressed) {
            // Ошибка сжатия файла
            return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), STATIC_OK));
        }


        // Строим временный путь к файлу jpg.
        String pathFileTmpJpg = ENVIRONMENT_PATH_TMP_PASSPORT_COMPANY + SLASH + temporaryFileTame + DOT + EXTENSION_JPG;


        // Генерируем имя (хеша md5) для уже сжатого файла
        String imageNameHash = generateFileName(pathFileTmpJpg);
        if (imageNameHash.equals("")) {
            // Ошибка получения хеша имени
            return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), STATIC_OK));
        }

        // Получаем размер файла после сжатием
        File fileAfterCompressionTmp = new File(pathFileTmpJpg);
        long fileAfterCompressionTmp1 = fileAfterCompressionTmp.length();

        // Из названия сжатого изображения строим относительный путь для сохранения изображения в конечную папку.
        List<String> charList = charList(imageNameHash);
        String group3 = charList.get(3);

        // Получаем файл у которого временное названием
        Path of = Path.of(pathFileTmpJpg);

        // Валидация даты
        LocalDate localDate = value.dateBirth();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedString = localDate.format(formatter);

        if (Boolean.TRUE.equals(fileBeforeCompressTmp1 > 10485760
                || fileAfterCompressionTmp1 < 10240
                || fileBeforeCompressTmp1 < fileAfterCompressionTmp1

                || value.phone().toString().length() != 10
                || validationRegExp.onlyNumbersRegExp(value.phone().toString())
                || serviceJpa.existsLogisticsCompanyByPhone(value.phone())

                || value.email().length() < 8
                || value.email().length() > 58
                || validationRegExp.emailValidationRegExp(value.email())
                // Проверка на существование email адреса находится ниже

                || value.password().length() < 6
                || value.password().length() > 24
                || validationRegExp.passwordValidationRegExp(value.password())

                || value.surname().length() < 1
                || value.surname().length() > 45
                || validationRegExp.onlyLettersCyrillic(value.surname())

                || value.name().length() < 1
                || value.name().length() > 45
                || validationRegExp.onlyLettersCyrillic(value.name())

                || value.middleName().length() < 1
                || value.middleName().length() > 45
                || validationRegExp.onlyLettersCyrillic(value.middleName())

                || value.dateBirth().toString().length() != 10
                || validationRegExp.validationDateRegExp(formattedString)

                || value.numberPassport().toString().length() != 10
                || validationRegExp.onlyNumbersRegExp(value.numberPassport().toString())
                || serviceJpa.existsLogisticsCompanyByNumberPassport(value.numberPassport())

                || value.inn().toString().length() != 12
                || validationRegExp.onlyNumbersRegExp(value.inn().toString())
                || serviceJpa.existsLogisticsCompanyByInn(value.inn())

                || String.valueOf(value.kpp()).length() != 9
                || validationRegExp.onlyNumbersRegExp(String.valueOf(value.kpp()))

                || String.valueOf(value.ogrn()).length() != 13
                || validationRegExp.onlyNumbersRegExp(String.valueOf(value.ogrn()))

                || value.nameCompany().length() < 1
                || value.nameCompany().length() > 30
                || validationRegExp.onlyLettersCyrillicAndNumbersRegExp(value.nameCompany())

                || value.region().length() != 7
                || validationRegExp.onlyLettersCyrillic(value.region())

                || value.city().length() < 2
                || value.city().length() > 25
                || validationRegExp.onlyLettersCyrillic(value.city())

                || value.street().length() < 2
                || value.street().length() > 50
                || validationRegExp.onlyLettersCyrillicAndNumbersRegExp(value.street())

                || value.building().length() < 1
                || value.building().length() > 11
                || validationRegExp.onlyLettersCyrillicAndNumbersRegExp(value.building())

                || value.house().length() < 1
                || value.house().length() > 11
                || validationRegExp.onlyLettersCyrillicAndNumbersRegExp(value.house())

                || String.valueOf(value.apartment()).length() < 1
                || String.valueOf(value.apartment()).length() > 4
                || validationRegExp.onlyNumbersRegExp(String.valueOf(value.apartment()))

                || value.bankAccount().length() != 20
                || validationRegExp.onlyNumbersRegExp(value.bankAccount())
                || serviceJpa.existsLogisticsCompanyByBankAccount(value.bankAccount())

                || String.valueOf(value.beakBank()).length() != 9
                || validationRegExp.onlyNumbersRegExp(String.valueOf(value.beakBank()))
                || serviceJpa.existsLogisticsCompanyByBeakBank(value.beakBank())

                || value.bankName().length() < 1
                || value.bankName().length() > 50
                || validationRegExp.onlyLettersCyrillicAndNumbersRegExp(value.bankName())

                || value.correspondentAccount().length() != 20
                || validationRegExp.onlyNumbersRegExp(value.correspondentAccount())

                || String.valueOf(value.innBank()).length() != 10
                || validationRegExp.onlyNumbersRegExp(String.valueOf(value.innBank()))

                || String.valueOf(value.kppBank()).length() != 9)
                || validationRegExp.onlyNumbersRegExp(String.valueOf(value.kppBank()))


        ) {
            // Так как не прошли фильтр/валидацию то, удаляем временный файл и возвращаем "OK"
            Files.deleteIfExists(of);
            return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), STATIC_OK));

        }


        if (Boolean.TRUE.equals(serviceJpa.existsLogisticsCompanyByEmail(value.email()))) {
            return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(),
                    "LogisticsCompany exist"),
                    HttpStatus.BAD_REQUEST);
        }


        // Строим пути для сохранения файла в конечную точку
        String pathFileEndPointJpg = ENVIRONMENT_PATH_PASSPORT_LOGISTICS_COMPANY + SLASH + group3 + SLASH + imageNameHash + DOT + EXTENSION_JPG;

        // Строим конечный путь где должен хранится файл
        String pathEndPoint = ENVIRONMENT_PATH_PASSPORT_LOGISTICS_COMPANY + SLASH + group3;

        // Относительный путь до файла. Для сохранения в БД
        String distPath = group3 + SLASH + imageNameHash + DOT + EXTENSION_JPG;

        // Это конечная точка назначение файла
        Path path = get(pathFileEndPointJpg);

        if (Files.exists(path)) {

            // Так как конечный файл существует, то, удаляем временный файл и возвращаем "OK"
            Files.deleteIfExists(of);

            return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), STATIC_OK));

        }

        // Конечный путь где должен хранится файл
        Path dir = get(pathEndPoint);

        if (Files.exists(dir)) {

            // Так как файла в конечной папке нет, а сама папка есть, то перемещаем файл из временной папки в постоянную
            // и сохраняем пользователя

            // Перемещаем файл из временной папку в конечную папку
            Files.move(of, path, StandardCopyOption.REPLACE_EXISTING);


        } else {

            // Если нет файла и нет папки, то выполняем тут.

            // Создаём конечную папку для файла
            Files.createDirectories(dir);

        }


        if (Files.exists(of)) {
            // Перемещаем файл из временной папку в конечную папку
            Files.move(of, path, StandardCopyOption.REPLACE_EXISTING);
        }


        if (Boolean.TRUE.equals(serviceJpa.existsLogisticsCompanyByImgPassport(path.toString()))) {
            return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), STATIC_OK));
        }


        LogisticsCompany logisticsCompany = new LogisticsCompany();

        logisticsCompany.setPhone(value.phone());
        logisticsCompany.setEmail(value.email());

        logisticsCompany.setPassword(encoder.encode(value.password()));

        logisticsCompany.setSurname(value.surname());
        logisticsCompany.setName(value.name());
        logisticsCompany.setMiddleName(value.middleName());
        logisticsCompany.setDateBirth(value.dateBirth());
        logisticsCompany.setNumberPassport(value.numberPassport());
        logisticsCompany.setImgPassport(distPath);

        logisticsCompany.setInn(value.inn());
        logisticsCompany.setKpp(value.kpp());
        logisticsCompany.setOgrn(value.ogrn());
        logisticsCompany.setCompanyName(value.nameCompany());

        logisticsCompany.setRegion(value.region());
        logisticsCompany.setCity(value.city());
        logisticsCompany.setStreet(value.street());
        logisticsCompany.setBuilding(value.building());
        logisticsCompany.setHouse(value.house());
        logisticsCompany.setApartment(value.apartment());

        logisticsCompany.setBankAccount(value.bankAccount());
        logisticsCompany.setBeakBank(value.beakBank());
        logisticsCompany.setBankName(value.bankName());
        logisticsCompany.setCorrespondentAccount(value.correspondentAccount());
        logisticsCompany.setInnBank(value.innBank());
        logisticsCompany.setKppBank(value.kppBank());

        logisticsCompany.setEnabled(false);
        logisticsCompany.setApproval(false);
        logisticsCompany.setToken(UUID.randomUUID().toString());
        logisticsCompany.setAccountNonLocked(true);


        logisticsCompany.setDateCreatedLogisticsCompany(Instant.now());
        logisticsCompany.setIpAddressRegistration(request.getRemoteAddr());
        logisticsCompany.setIpAddressRegConfirm(request.getRemoteAddr());
        logisticsCompany.setIpAddressFirstEntrance(request.getRemoteAddr());
        logisticsCompany.setIpAddressLastEntrance(request.getRemoteAddr());

        // Добавляем дату удаления аккаунта если не будет подтверждён адрес электронной почты,
        NotActivatedLogisticsCompany notActivatedLogisticsCompany = new NotActivatedLogisticsCompany();
        notActivatedLogisticsCompany.setDateDeletionLogisticsCompany(Instant.now().plus(1, ChronoUnit.DAYS));
        notActivatedLogisticsCompany.setActive(false);

        // Синхронизируем между собой user и notActivatedUser или по простому - соединяем между собой
        logisticsCompany.setNotActivatedLogisticsCompany(notActivatedLogisticsCompany);
        notActivatedLogisticsCompany.setLogisticsCompany(logisticsCompany);

        Set<RoleLogisticsCompany> roleLogisticsCompanyPeople = new LinkedHashSet<>();
        RoleLogisticsCompany roleLogisticsCompany = serviceJpa.findRoleLogisticsCompanyByRoleEnum(RoleEnum.ROLE_LOGISTICS_COMPANY);
        roleLogisticsCompanyPeople.add(roleLogisticsCompany);
        logisticsCompany.setLogisticsCompanyRoles(roleLogisticsCompanyPeople);

        serviceJpa.saveLogisticsCompany(logisticsCompany);

        // Отправляем письмо пользователю
        sendEmail.confirmEmailLogisticsCompany(request.getServerName(), logisticsCompany.getToken());


        return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), STATIC_OK));


    }



    @PostMapping(value = "/confirmEmailLogisticsCompany", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MessageResponse> confirmEmail(@RequestBody Token token, HttpServletRequest request) {

        limitLogin.addCache(request.getRemoteAddr());

        if (token.token().length() != 36 || validationRegExp.validationTokenRegExp(token.token()) || limitLogin.isBlocked(request.getRemoteAddr())) {
            return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(),
                    STATIC_OK),
                    HttpStatus.OK);
        }

        LogisticsCompany logisticsCompany = serviceJpa.findLogisticsCompanyByToken(token.token()).orElse(new LogisticsCompany());

        if (logisticsCompany.getToken() == null || logisticsCompany.getToken().isEmpty()) {
            return customMessageConfirm();
        }

        logisticsCompany.setIpAddressRegConfirm(request.getRemoteAddr());
        logisticsCompany.setEnabled(true);
        logisticsCompany.setToken(null);

        NotActivatedLogisticsCompany notActivatedUser = logisticsCompany.getNotActivatedLogisticsCompany();

        notActivatedUser.setActive(true);

        notActivatedUser.setLogisticsCompany(logisticsCompany);
        logisticsCompany.setNotActivatedLogisticsCompany(notActivatedUser);

        serviceJpa.saveLogisticsCompany(logisticsCompany);

        return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), STATIC_OK));

    }


}
