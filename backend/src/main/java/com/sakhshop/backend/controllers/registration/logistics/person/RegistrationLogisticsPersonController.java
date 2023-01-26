package com.sakhshop.backend.controllers.registration.logistics.person;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sakhshop.backend.email.SendEmail;
import com.sakhshop.backend.enam.RoleEnum;
import com.sakhshop.backend.models.activation.NotActivatedLogisticsPerson;
import com.sakhshop.backend.models.logistics.person.LogisticsPerson;
import com.sakhshop.backend.models.payload.request.logistics.person.RegistrationLogisticsPersonRequest;
import com.sakhshop.backend.models.payload.response.MessageResponse;
import com.sakhshop.backend.models.role.RoleLogisticsPerson;
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
public class RegistrationLogisticsPersonController {


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

    public RegistrationLogisticsPersonController(ValidationRegExp validationRegExp, ServiceJpa serviceJpa, PasswordEncoder encoder, SendEmail sendEmail, LoginAttemptService limitLogin) {
        this.validationRegExp = validationRegExp;
        this.serviceJpa = serviceJpa;
        this.encoder = encoder;
        this.sendEmail = sendEmail;
        this.limitLogin = limitLogin;
    }



    @PostMapping(value = "/registrationLogisticsPerson", consumes = "multipart/form-data", produces = "application/json")
    public ResponseEntity<MessageResponse> registerLogisticsPerson(@RequestParam(value = "imagePassport") MultipartFile multipartFilePassport,
                                                                   @RequestParam(value = "imageMedical") MultipartFile multipartFileMedical,
                                                                   @RequestParam(value = "registrationLogisticsPerson") String registrationLogisticsPersonRequest,
                                                                    HttpServletRequest request) throws IOException {



        // Конвертируем LogisticsPerson в объект LogisticsPersonRequest
        ObjectMapper objectMapper = new ObjectMapper();

        RegistrationLogisticsPersonRequest value = objectMapper.readValue(registrationLogisticsPersonRequest, RegistrationLogisticsPersonRequest.class);



        // Получаем расширение файла на основе MimeType
        String extensionPassport = getExtension(Objects.requireNonNull(multipartFilePassport.getContentType()));
        String extensionMedical = getExtension(Objects.requireNonNull(multipartFileMedical.getContentType()));



        if (Objects.equals(extensionPassport, "") || Objects.equals(extensionMedical, "")) {
            return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), STATIC_OK));
        }

        // Генерируем случайное временное имя файла
        String temporaryFileTamePassport = UUID.randomUUID().toString();
        String temporaryFileTameMedical = UUID.randomUUID().toString();

        // Создаём путь к файлу у которого временное имя
        String pathFileTmpPassport = ENVIRONMENT_PATH_TMP_PASSPORT_PERSON + SLASH + temporaryFileTamePassport + DOT + extensionPassport;
        String pathFileTmpMedical = ENVIRONMENT_PATH_TMP_PASSPORT_PERSON + SLASH + temporaryFileTameMedical + DOT + extensionMedical;






        // Сохраняем этот файл с временным именем
        Path sourcePathFileTmpPassport = get(pathFileTmpPassport);
        multipartFilePassport.transferTo(sourcePathFileTmpPassport);

        Path sourcePathFileTmpMedical = get(pathFileTmpMedical);
        multipartFileMedical.transferTo(sourcePathFileTmpMedical);






        // Получаем размер файла перед сжатием
        File fileBeforeCompressTmpPassport = new File(pathFileTmpPassport);
        long fileBeforeCompressTmp1Passport = fileBeforeCompressTmpPassport.length();

        File fileBeforeCompressTmpMedical = new File(pathFileTmpMedical);
        long fileBeforeCompressTmp1Medical = fileBeforeCompressTmpMedical.length();







        // Сжимаем изображение + конвертируем в jpg если это png + очищаем метаданные
        // После конвертации получаются два файла, один оригинал png и второй на выходе jpg, при условии, что исходный был png
        boolean compressedPassport = compressionAndConversion(ENVIRONMENT_PATH_TMP_PASSPORT_PERSON, temporaryFileTamePassport, extensionPassport);
        boolean compressedMedical = compressionAndConversion(ENVIRONMENT_PATH_TMP_PASSPORT_PERSON, temporaryFileTameMedical, extensionMedical);

        if (!compressedPassport || !compressedMedical) {
            // Ошибка сжатия файла
            return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), STATIC_OK));
        }


        // Строим временный путь к файлу jpg.
        String pathFileTmpJpgPassport = ENVIRONMENT_PATH_TMP_PASSPORT_PERSON + SLASH + temporaryFileTamePassport + DOT + EXTENSION_JPG;
        String pathFileTmpJpgMedical = ENVIRONMENT_PATH_TMP_PASSPORT_PERSON + SLASH + temporaryFileTameMedical + DOT + EXTENSION_JPG;




        // Генерируем имя (хеша md5) для уже сжатого файла
        String imageNameHashPassport = generateFileName(pathFileTmpJpgPassport);
        String imageNameHashMedical = generateFileName(pathFileTmpJpgMedical);
        if (imageNameHashPassport.equals("") || imageNameHashMedical.equals("")) {
            // Ошибка получения хеша имени
            return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), STATIC_OK));
        }




        // Получаем размер файла после сжатием
        File fileAfterCompressionTmpPassport = new File(pathFileTmpJpgPassport);
        long fileAfterCompressionTmp1Passport = fileAfterCompressionTmpPassport.length();

        File fileAfterCompressionTmpMedical = new File(pathFileTmpJpgMedical);
        long fileAfterCompressionTmp1Medical = fileAfterCompressionTmpMedical.length();





        // Из названия сжатого изображения строим относительный путь для сохранения изображения в конечную папку.
        List<String> charListPassport = charList(imageNameHashPassport);
        String group3Passport = charListPassport.get(3);

        List<String> charListMedical = charList(imageNameHashMedical);
        String group3Medical = charListMedical.get(3);






        // Получаем файл у которого временное названием
        Path ofPassport = Path.of(pathFileTmpJpgPassport);
        Path ofMedical = Path.of(pathFileTmpJpgMedical);





        // Валидация даты
        LocalDate localDate = value.dateBirth();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedString = localDate.format(formatter);




        if (Boolean.TRUE.equals(fileBeforeCompressTmp1Passport > 10485760
                || fileAfterCompressionTmp1Passport < 10240
                || fileBeforeCompressTmp1Passport < fileAfterCompressionTmp1Passport

                || fileBeforeCompressTmp1Medical > 10485760
                || fileAfterCompressionTmp1Medical < 10240
                || fileBeforeCompressTmp1Medical < fileAfterCompressionTmp1Medical

                || value.phone().toString().length() != 10
                || validationRegExp.onlyNumbersRegExp(value.phone().toString())
                || serviceJpa.existsLogisticsPersonByPhone(value.phone())

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
                || serviceJpa.existsLogisticsPersonByNumberPassport(value.numberPassport())

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
                || serviceJpa.existsLogisticsPersonByBankAccount(value.bankAccount())

                || String.valueOf(value.beakBank()).length() != 9
                || validationRegExp.onlyNumbersRegExp(String.valueOf(value.beakBank()))
                || serviceJpa.existsLogisticsPersonByBeakBank(value.beakBank())

                || value.bankName().length() < 1
                || value.bankName().length() > 50
                || validationRegExp.onlyLettersCyrillicAndNumbersRegExp(value.bankName())

                || value.correspondentAccount().length() != 20
                || validationRegExp.onlyNumbersRegExp(value.correspondentAccount())

                || String.valueOf(value.innBank()).length() != 10
                || validationRegExp.onlyNumbersRegExp(String.valueOf(value.innBank()))

                || String.valueOf(value.kppBank()).length() != 9
                || validationRegExp.onlyNumbersRegExp(String.valueOf(value.kppBank())))


        ) {
            // Так как не прошли фильтр/валидацию то, удаляем временный файл и возвращаем "OK"
            Files.deleteIfExists(ofPassport);
            Files.deleteIfExists(ofMedical);
            return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), STATIC_OK));

        }


        if (Boolean.TRUE.equals(serviceJpa.existsLogisticsPersonByEmail(value.email()))) {
            return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(),
                    "LogisticsPerson exist"),
                    HttpStatus.BAD_REQUEST);
        }


        // Строим пути для сохранения файла в конечную точку
        String pathFileEndPointJpgPassport = ENVIRONMENT_PATH_PASSPORT_LOGISTICS_PERSON + SLASH + group3Passport + SLASH + imageNameHashPassport + DOT + EXTENSION_JPG;
        String pathFileEndPointJpgMedical = ENVIRONMENT_PATH_PASSPORT_LOGISTICS_PERSON + SLASH + group3Medical + SLASH + imageNameHashMedical + DOT + EXTENSION_JPG;



        // Строим конечный путь где должен хранится файл
        String pathEndPointPassport = ENVIRONMENT_PATH_PASSPORT_LOGISTICS_PERSON + SLASH + group3Passport;
        String pathEndPointMedical = ENVIRONMENT_PATH_PASSPORT_LOGISTICS_PERSON + SLASH + group3Medical;



        // Относительный путь до файла. Для сохранения в БД
        String distPathPassport = group3Passport + SLASH + imageNameHashPassport + DOT + temporaryFileTamePassport;
        String distPathMedical = group3Medical + SLASH + imageNameHashMedical + DOT + temporaryFileTameMedical;












        // Это конечная точка назначение файла
        Path pathPassport = get(pathFileEndPointJpgPassport);
        Path pathMedical = get(pathFileEndPointJpgMedical);


        if (Files.exists(pathPassport)) {
            // Так как конечный файл существует, то, удаляем временный файл и возвращаем "OK"
            Files.deleteIfExists(ofPassport);

            return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), STATIC_OK));
        }

        if (Files.exists(pathMedical)) {
            // Так как конечный файл существует, то, удаляем временный файл и возвращаем "OK"
            Files.deleteIfExists(ofMedical);

            return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), STATIC_OK));
        }














        // Конечный путь где должен хранится файл
        Path dirPassport = get(pathEndPointPassport);
        Path dirMedical = get(pathEndPointMedical);

        if (Files.exists(dirPassport)) {
            // Так как файла в конечной папке нет, а сама папка есть, то перемещаем файл из временной папки в постоянную
            // и сохраняем пользователя

            // Перемещаем файл из временной папку в конечную папку
            Files.move(ofPassport, pathPassport, StandardCopyOption.REPLACE_EXISTING);
        } else {
            // Если нет файла и нет папки, то выполняем тут.

            // Создаём конечную папку для файла
            Files.createDirectories(dirPassport);
        }







        if (Files.exists(dirMedical)) {
            // Так как файла в конечной папке нет, а сама папка есть, то перемещаем файл из временной папки в постоянную
            // и сохраняем пользователя

            // Перемещаем файл из временной папку в конечную папку
            Files.move(ofMedical, pathMedical, StandardCopyOption.REPLACE_EXISTING);
        } else {
            // Если нет файла и нет папки, то выполняем тут.

            // Создаём конечную папку для файла
            Files.createDirectories(dirMedical);
        }














        if (Files.exists(ofPassport)) {
            // Перемещаем файл из временной папку в конечную папку
            Files.move(ofPassport, pathPassport, StandardCopyOption.REPLACE_EXISTING);
        }

        if (Files.exists(ofMedical)) {
            // Перемещаем файл из временной папку в конечную папку
            Files.move(ofMedical, pathMedical, StandardCopyOption.REPLACE_EXISTING);
        }













        if (Boolean.TRUE.equals(serviceJpa.existsLogisticsPersonByImgPassport(pathPassport.toString())
                || serviceJpa.existsLogisticsPersonByImgMedicalCheckup(pathPassport.toString()))) {

            return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), STATIC_OK));

        }










        LogisticsPerson logisticsPerson = new LogisticsPerson();

        logisticsPerson.setPhone(value.phone());
        logisticsPerson.setEmail(value.email());

        logisticsPerson.setPassword(encoder.encode(value.password()));

        logisticsPerson.setSurname(value.surname());
        logisticsPerson.setName(value.name());
        logisticsPerson.setMiddleName(value.middleName());
        logisticsPerson.setDateBirth(value.dateBirth());
        logisticsPerson.setNumberPassport(value.numberPassport());
        logisticsPerson.setImgPassport(distPathPassport);

        logisticsPerson.setImgMedicalCheckup(distPathMedical);
        logisticsPerson.setMedicalCheckup(false);

        logisticsPerson.setRegion(value.region());
        logisticsPerson.setCity(value.city());
        logisticsPerson.setStreet(value.street());
        logisticsPerson.setBuilding(value.building());
        logisticsPerson.setHouse(value.house());
        logisticsPerson.setApartment(value.apartment());

        logisticsPerson.setBankAccount(value.bankAccount());
        logisticsPerson.setBeakBank(value.beakBank());
        logisticsPerson.setBankName(value.bankName());
        logisticsPerson.setCorrespondentAccount(value.correspondentAccount());
        logisticsPerson.setInnBank(value.innBank());
        logisticsPerson.setKppBank(value.kppBank());

        logisticsPerson.setEnabled(false);
        logisticsPerson.setApproval(false);
        logisticsPerson.setToken(UUID.randomUUID().toString());
        logisticsPerson.setAccountNonLocked(true);


        logisticsPerson.setDateCreatedLogisticsPerson(Instant.now());
        logisticsPerson.setIpAddressRegistration(request.getRemoteAddr());
        logisticsPerson.setIpAddressRegConfirm(request.getRemoteAddr());
        logisticsPerson.setIpAddressFirstEntrance(request.getRemoteAddr());
        logisticsPerson.setIpAddressLastEntrance(request.getRemoteAddr());

        // Добавляем дату удаления аккаунта если не будет подтверждён адрес электронной почты,
        NotActivatedLogisticsPerson notActivatedLogisticsPerson = new NotActivatedLogisticsPerson();
        notActivatedLogisticsPerson.setDateDeletionLogisticsPerson(Instant.now().plus(1, ChronoUnit.DAYS));
        notActivatedLogisticsPerson.setActive(false);

        // Синхронизируем между собой user и notActivatedUser или по простому - соединяем между собой
        logisticsPerson.setNotActivatedLogisticsPerson(notActivatedLogisticsPerson);
        notActivatedLogisticsPerson.setLogisticsPerson(logisticsPerson);

        Set<RoleLogisticsPerson> roleLogisticsPersonPeople = new LinkedHashSet<>();
        RoleLogisticsPerson roleLogisticsPerson = serviceJpa.findRoleLogisticsPersonByRoleEnum(RoleEnum.ROLE_LOGISTICS_PERSON);
        roleLogisticsPersonPeople.add(roleLogisticsPerson);
        logisticsPerson.setLogisticsPersonRoles(roleLogisticsPersonPeople);

        serviceJpa.saveLogisticsPerson(logisticsPerson);

        // Отправляем письмо пользователю
        sendEmail.confirmEmailLogisticsPerson(request.getServerName(), logisticsPerson.getToken());


        return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), STATIC_OK));


    }



    @PostMapping(value = "/confirmEmailLogisticsPerson", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MessageResponse> confirmEmail(@RequestBody Token token, HttpServletRequest request) {

        limitLogin.addCache(request.getRemoteAddr());

        if (token.token().length() != 36 || validationRegExp.validationTokenRegExp(token.token()) || limitLogin.isBlocked(request.getRemoteAddr())) {
            return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(),
                    STATIC_OK),
                    HttpStatus.OK);
        }

        LogisticsPerson logisticsPerson = serviceJpa.findLogisticsPersonByToken(token.token()).orElse(new LogisticsPerson());

        if (logisticsPerson.getToken() == null || logisticsPerson.getToken().isEmpty()) {
            return customMessageConfirm();
        }

        logisticsPerson.setIpAddressRegConfirm(request.getRemoteAddr());
        logisticsPerson.setEnabled(true);
        logisticsPerson.setToken(null);

        NotActivatedLogisticsPerson notActivatedUser = logisticsPerson.getNotActivatedLogisticsPerson();

        notActivatedUser.setActive(true);

        notActivatedUser.setLogisticsPerson(logisticsPerson);
        logisticsPerson.setNotActivatedLogisticsPerson(notActivatedUser);

        serviceJpa.saveLogisticsPerson(logisticsPerson);

        return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), STATIC_OK));

    }


}
