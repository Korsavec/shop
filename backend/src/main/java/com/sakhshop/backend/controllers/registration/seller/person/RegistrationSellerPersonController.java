package com.sakhshop.backend.controllers.registration.seller.person;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sakhshop.backend.email.SendEmail;
import com.sakhshop.backend.enam.RoleEnum;
import com.sakhshop.backend.models.activation.NotActivatedSellerPerson;
import com.sakhshop.backend.models.payload.request.seller.person.RegistrationSellerPersonRequest;
import com.sakhshop.backend.models.payload.response.MessageResponse;
import com.sakhshop.backend.models.role.RoleSellerPerson;
import com.sakhshop.backend.models.seller.person.SellerPerson;
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
public class RegistrationSellerPersonController {

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

    public RegistrationSellerPersonController(ValidationRegExp validationRegExp, ServiceJpa serviceJpa, PasswordEncoder encoder, SendEmail sendEmail, LoginAttemptService limitLogin) {
        this.validationRegExp = validationRegExp;
        this.serviceJpa = serviceJpa;
        this.encoder = encoder;
        this.sendEmail = sendEmail;
        this.limitLogin = limitLogin;
    }



    @PostMapping(value = "/registrationSellerPerson", consumes = "multipart/form-data", produces = "application/json")
    public ResponseEntity<MessageResponse> registerSellerPerson(@RequestParam(value = "image") MultipartFile multipartFile,
                                                  @RequestParam(value = "registrationSellerPerson") String registrationSellerPersonRequest,
                                                  HttpServletRequest request) throws IOException {

        // Конвертируем sellerPerson в объект SellerPersonRequest
        ObjectMapper objectMapper = new ObjectMapper();
        RegistrationSellerPersonRequest value = objectMapper.readValue(registrationSellerPersonRequest, RegistrationSellerPersonRequest.class);

        // Получаем расширение файла на основе MimeType
        String extension = getExtension(Objects.requireNonNull(multipartFile.getContentType()));

        if (Objects.equals(extension, "")) {
            return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), STATIC_OK));
        }

        // Генерируем случайное временное имя файла
        String temporaryFileTame = UUID.randomUUID().toString();

        // Создаём путь к файлу у которого временное имя
        String pathFileTmp = FILE_SYSTEM_PATH_TMP_PASSPORT + SLASH + temporaryFileTame + DOT + extension;

        // Сохраняем этот файл с временным именем
        Path sourcePathFileTmp = get(pathFileTmp);
        multipartFile.transferTo(sourcePathFileTmp);

        // Получаем размер файла перед сжатием
        File fileBeforeCompressTmp = new File(pathFileTmp);
        long fileBeforeCompressTmp1 = fileBeforeCompressTmp.length();

        // Сжимаем изображение + конвертируем в jpg если это png + очищаем метаданные
        // После конвертации получаются два файла, один оригинал png и второй на выходе jpg, при условии, что исходный был png
        boolean compressed = compressionAndConversion(FILE_SYSTEM_PATH_TMP_PASSPORT, temporaryFileTame, extension);

        if (!compressed) {
            // Ошибка сжатия файла
            return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), STATIC_OK));
        }


        // Строим временный путь к файлу jpg.
        String pathFileTmpJpg = FILE_SYSTEM_PATH_TMP_PASSPORT + SLASH + temporaryFileTame + DOT + EXTENSION_JPG;


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
                || serviceJpa.existsByPhone(value.phone())

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
                || serviceJpa.existsByNumberPassportSellerPerson(value.numberPassport())

                || value.inn().toString().length() != 12
                || validationRegExp.onlyNumbersRegExp(value.inn().toString())
                || serviceJpa.existsByInnSellerPerson(value.inn())

                || value.shopName().length() < 3
                || value.shopName().length() > 30
                || validationRegExp.onlyLettersCyrillicAndNumbersRegExp(value.shopName())
                || serviceJpa.existsByShopNameSellerPerson(value.shopName())

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
                || serviceJpa.existsByBankAccount(value.bankAccount())

                || String.valueOf(value.beakBank()).length() != 9
                || validationRegExp.onlyNumbersRegExp(String.valueOf(value.beakBank()))
                || serviceJpa.existsByBeakBank(value.beakBank())

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


        if (Boolean.TRUE.equals(serviceJpa.existsByEmailSellerPerson(value.email()))) {
            return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(),
                    "SellerPerson exist"),
                    HttpStatus.BAD_REQUEST);
        }


        // Строим пути для сохранения файла в конечную точку
        String pathFileEndPointJpg = FILE_SYSTEM_PATH_RESOURCES_PASSPORT + SLASH + group3 + SLASH + imageNameHash + DOT + EXTENSION_JPG;

        // Строим конечный путь где должен хранится файл
        String pathEndPoint = FILE_SYSTEM_PATH_RESOURCES_PASSPORT + SLASH + group3;

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


        if (Boolean.TRUE.equals(serviceJpa.existsByImgPassport(path.toString()))) {
            return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), STATIC_OK));
        }


        SellerPerson sellerPerson = new SellerPerson();
        sellerPerson.setPhone(value.phone());
        sellerPerson.setEmail(value.email());

        sellerPerson.setPassword(encoder.encode(value.password()));

        sellerPerson.setSurname(value.surname());
        sellerPerson.setName(value.name());
        sellerPerson.setMiddleName(value.middleName());
        sellerPerson.setDateBirth(value.dateBirth());
        sellerPerson.setNumberPassport(value.numberPassport());
        sellerPerson.setInn(value.inn());
        sellerPerson.setShopName(value.shopName());
        sellerPerson.setImgPassport(distPath);
        sellerPerson.setRegion(value.region());
        sellerPerson.setCity(value.city());
        sellerPerson.setStreet(value.street());
        sellerPerson.setBuilding(value.building());
        sellerPerson.setHouse(value.house());
        sellerPerson.setApartment(value.apartment());
        sellerPerson.setBankAccount(value.bankAccount());
        sellerPerson.setBeakBank(value.beakBank());
        sellerPerson.setBankName(value.bankName());
        sellerPerson.setCorrespondentAccount(value.correspondentAccount());
        sellerPerson.setInnBank(value.innBank());
        sellerPerson.setKppBank(value.kppBank());
        sellerPerson.setEnabled(false);
        sellerPerson.setApproval(false);
        sellerPerson.setToken(UUID.randomUUID().toString());
        sellerPerson.setAccountNonLocked(true);


        sellerPerson.setDateCreatedSellerPerson(Instant.now());
        sellerPerson.setIpAddressRegistration(request.getRemoteAddr());
        sellerPerson.setIpAddressRegConfirm(request.getRemoteAddr());
        sellerPerson.setIpAddressFirstEntrance(request.getRemoteAddr());
        sellerPerson.setIpAddressLastEntrance(request.getRemoteAddr());

        // Добавляем дату удаления аккаунта если не будет подтверждён адрес электронной почты,
        NotActivatedSellerPerson notActivatedSellerPerson = new NotActivatedSellerPerson();
        notActivatedSellerPerson.setDateDeletionSellerPerson(Instant.now().plus(1, ChronoUnit.DAYS));
        notActivatedSellerPerson.setActive(false);

        // Синхронизируем между собой user и notActivatedUser или по простому - соединяем между собой
        sellerPerson.setNotActivatedSellerPerson(notActivatedSellerPerson);
        notActivatedSellerPerson.setSellerPerson(sellerPerson);

        Set<RoleSellerPerson> roleSellerPeople = new LinkedHashSet<>();
        RoleSellerPerson roleSellerPerson = serviceJpa.findByRoleEnumSellerPerson(RoleEnum.ROLE_SELLER_PERSON);
        roleSellerPeople.add(roleSellerPerson);
        sellerPerson.setRoleSellerPersons(roleSellerPeople);


        serviceJpa.saveSellerPerson(sellerPerson);

        // Отправляем письмо пользователю
        sendEmail.confirmEmailSellerPerson(request.getServerName(), sellerPerson.getToken());


        return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), STATIC_OK));


    }



    @PostMapping(value = "/confirmEmailSellerPerson", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MessageResponse> confirmEmail(@RequestBody Token token, HttpServletRequest request) {

        limitLogin.addCache(request.getRemoteAddr());

        if (token.token().length() != 36 || validationRegExp.validationTokenRegExp(token.token()) || limitLogin.isBlocked(request.getRemoteAddr())) {
            return new ResponseEntity<>(new MessageResponse(HttpStatus.BAD_REQUEST.value(),
                    STATIC_OK),
                    HttpStatus.OK);
        }

        SellerPerson sellerPerson = serviceJpa.findSellerPersonByToken(token.token()).orElse(new SellerPerson());

        if (sellerPerson.getToken() == null || sellerPerson.getToken().isEmpty()) {
            return new ResponseEntity<>(new MessageResponse(HttpStatus.NOT_FOUND.value(),
                    STATIC_OK),
                    HttpStatus.OK);
        }

        sellerPerson.setIpAddressRegConfirm(request.getRemoteAddr());
        sellerPerson.setEnabled(true);
        sellerPerson.setToken(null);

        NotActivatedSellerPerson notActivatedUser = sellerPerson.getNotActivatedSellerPerson();

        notActivatedUser.setActive(true);

        notActivatedUser.setSellerPerson(sellerPerson);
        sellerPerson.setNotActivatedSellerPerson(notActivatedUser);

        serviceJpa.saveSellerPerson(sellerPerson);

        return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), STATIC_OK));

    }



}
