//package com.example.mybatis_1.utils;
//
//import java.awt.Graphics2D;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.ConnectException;
//import java.net.InetAddress;
//import java.net.MalformedURLException;
//import java.net.URISyntaxException;
//import java.net.URL;
//import java.net.UnknownHostException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.FileSystems;
//import java.nio.file.Path;
//import java.sql.SQLException;
//import java.text.SimpleDateFormat;
//import java.time.DayOfWeek;
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.time.OffsetDateTime;
//import java.time.ZoneId;
//import java.time.ZoneOffset;
//import java.time.ZonedDateTime;
//import java.time.format.DateTimeFormatter;
//import java.time.temporal.ChronoUnit;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Base64;
//import java.util.Calendar;
//import java.util.Collection;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Objects;
//import java.util.Set;
//import java.util.TimeZone;
//import java.util.UUID;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.stream.Collectors;
//import javax.imageio.IIOImage;
//import javax.imageio.ImageIO;
//import javax.imageio.ImageWriteParam;
//import javax.imageio.ImageWriter;
//import javax.servlet.http.HttpServletRequest;
//import javax.xml.bind.DatatypeConverter;
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.io.FilenameUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang3.exception.ExceptionUtils;
//import org.apache.http.NoHttpResponseException;
//import org.apache.ibatis.session.RowBounds;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.apache.tika.config.TikaConfig;
//import org.apache.tika.detect.Detector;
//import org.apache.tika.io.TemporaryResources;
//import org.apache.tika.io.TikaInputStream;
//import org.apache.tika.metadata.Metadata;
//import org.apache.tika.mime.MediaType;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.client.HttpServerErrorException.ServiceUnavailable;
//import org.springframework.web.client.RestClientResponseException;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.web.multipart.MultipartFile;
//import com.fasterxml.jackson.databind.ObjectMapper;
///**
// * The Class CommonUtils.
// */
//public class CommonUtils {
//
//  /** The Constant AUTHORITIES_SEPARATOR. */
//  public static final String AUTHORITIES_SEPARATOR = ",";
//
//  /** The Constant BYTE_TO_HEX_ARRAY. */
//  private static final byte[] BYTE_TO_HEX_ARRAY =
//      "0123456789ABCDEF".getBytes(StandardCharsets.UTF_8);
//
//  /** The Constant REQUEST_IP_HEADERS. */
//  private static final String[] REQUEST_IP_HEADERS = {
//      "X-Forwarded-For",
//      "Proxy-Client-IP",
//      "WL-Proxy-Client-IP",
//      "HTTP_X_FORWARDED_FOR",
//      "HTTP_X_FORWARDED",
//      "HTTP_X_CLUSTER_CLIENT_IP",
//      "HTTP_CLIENT_IP",
//      "HTTP_FORWARDED_FOR",
//      "HTTP_FORWARDED",
//      "HTTP_VIA",
//      "REMOTE_ADDR"
//  };
//
//  /** The Constant COMMA. */
//  private static final String COMMA = ",";
//
//  /** The Constant COLON. */
//  private static final String COLON = ":";
//
//  /** The Constant SLASH. */
//  private static final String SLASH = "/";
//
//  /** The Constant DOT. */
//  private static final String DOT = ".";
//
//  /** The zone offset. */
//  private static ZoneOffset zoneOffset = null;
//
//  /** The host ip. */
//  private static String hostIp = null;
//
//  /** The Constant AVERAGE_RADIUS_OF_EARTH_KM. */
//  public static final double AVERAGE_RADIUS_OF_EARTH_KM = 6371;
//
//  /**
//   * At end of day.
//   *
//   * @param date the date
//   * @return the instant
//   */
//  public static Instant atEndOfDay(Instant date) {
//    Calendar calendar = Calendar.getInstance();
//    calendar.setTime(Date.from(date));
//    calendar.set(Calendar.HOUR_OF_DAY, 23);
//    calendar.set(Calendar.MINUTE, 59);
//    calendar.set(Calendar.SECOND, 59);
//    calendar.set(Calendar.MILLISECOND, 999);
//    return calendar.getTime().toInstant();
//  }
//
//  /**
//   * At start of day.
//   *
//   * @param date the date
//   * @return the instant
//   */
//  public static Instant atStartOfDay(Instant date) {
//    Calendar calendar = Calendar.getInstance();
//    calendar.setTime(Date.from(date));
//    calendar.set(Calendar.HOUR_OF_DAY, 0);
//    calendar.set(Calendar.MINUTE, 0);
//    calendar.set(Calendar.SECOND, 0);
//    calendar.set(Calendar.MILLISECOND, 0);
//    return calendar.getTime().toInstant();
//  }
//
//  /**
//   * Builds the row bounds.
//   *
//   * @param pageNum the page num
//   * @param pageSize the page size
//   * @return the row bounds
//   */
//  public static RowBounds buildRowBounds(Integer pageNum, Integer pageSize) {
//    RowBounds rowBounds = null;
//
//    if (pageNum == null || pageSize == null || pageNum == 0) {
//      rowBounds = new RowBounds();
//    } else {
//      rowBounds = new RowBounds(pageNum, pageSize);
//    }
//
//    return rowBounds;
//  }
//
//  /**
//   * Calculate distance in kilometer.
//   *
//   * @param latitude1 the latitude 1
//   * @param longitude1 the longitude 1
//   * @param latitude2 the latitude 2
//   * @param longitude2 the longitude 2
//   * @return the double
//   */
//  public static double calculateDistanceInKilometer(double latitude1, double longitude1,
//      double latitude2, double longitude2) {
//    double c = 0;
//    if (latitude1 == 0 || longitude1 == 0 || latitude2 == 0 || longitude2 == 0) {
//      c = 0;
//    } else {
//      double latDistance = Math.toRadians(latitude1 - latitude2);
//      double lngDistance = Math.toRadians(longitude1 - longitude2);
//
//      double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
//          + Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2))
//              * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);
//
//      c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//    }
//    return AVERAGE_RADIUS_OF_EARTH_KM * c;
//  }
//
//  /**
//   * Check cdw.
//   *
//   * @param insuranceType the insurance type
//   * @return the boolean
//   */
//  public static Boolean checkCdw(String insuranceType) {
//    if (CDWType.NO.value().equals(insuranceType)) {
//      return false;
//    }
//    if (CDWType.YES.value().equals(insuranceType)) {
//      return true;
//    }
//    return null;
//  }
//
//  /**
//   * Convert iso time to instant.
//   *
//   * @param dateInput the date input
//   * @return the instant
//   */
//  public static Instant convertIsoTimeToInstant(String dateInput) {
//    if (StringUtils.isEmpty(dateInput)) {
//      return null;
//    }
//
//    try {
//      if (StringUtils.isNumeric(dateInput)) {
//        return Instant.ofEpochSecond(Long.valueOf(dateInput));
//      } else {
//        return Instant.parse(dateInput);
//      }
//    } catch (Exception e) {
//      return null;
//    }
//  }
//
//  /**
//   * Convert iso time to string.
//   *
//   * @param dateInput the date input
//   * @return the string
//   * @throws Exception the exception
//   */
//  public static String convertIsoTimeToString(String dateInput) throws Exception {
//    if (StringUtils.isNumeric(dateInput)) {
//      return convertUnixTimeToString(Long.valueOf(dateInput),
//          Constants.FORMAT.DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_Z, null);
//    } else {
//      return convertIsoTimeToString(dateInput, Constants.FORMAT.DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS_Z,
//          null);
//    }
//  }
//
//  /**
//   * Convert iso time to string.
//   *
//   * @param dateInput the date input
//   * @param format the format
//   * @return the string
//   */
//  public static String convertIsoTimeToString(String dateInput, String format) {
//    return convertIsoTimeToString(dateInput, format, null);
//  }
//
//  /**
//   * Convert iso time to string.
//   *
//   * @param dateInput the date input
//   * @param format the format
//   * @param timeZone the time zone
//   * @return the string
//   */
//  public static String convertIsoTimeToString(String dateInput, String format, String timeZone) {
//    Instant instant = Instant.parse(dateInput);
//    SimpleDateFormat formatter = new SimpleDateFormat(format);
//    if (timeZone != null) {
//      formatter.setTimeZone(TimeZone.getTimeZone(timeZone));
//    } else {
//      formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
//    }
//    return formatter.format(Date.from(instant));
//  }
//
//  /**
//   * Convert iso time to string for rest COCOS.
//   *
//   * @param dateInput the date input
//   * @return the string
//   * @throws Exception the exception
//   */
//  public static String convertIsoTimeToStringForRestCocos(String dateInput) throws Exception {
//    if (StringUtils.isNumeric(dateInput)) {
//      return convertUnixTimeToString(Long.valueOf(dateInput),
//          Constants.FORMAT.DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS, "GMT+8");
//    } else {
//      return convertIsoTimeToString(dateInput, Constants.FORMAT.DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS,
//          "GMT+8");
//    }
//  }
//
//  /**
//   * Convert mileage to km.
//   *
//   * @param mileage the mileage
//   * @return the double
//   */
//  public static Double convertMileageToKm(Double mileage) {
//    return Math.round(mileage * 1.60934 * 100.0) / 100.0;
//  }
//
//  /**
//   * Convert multipart file to base 64.
//   *
//   * @param file the file
//   * @return the string
//   * @throws Exception the exception
//   */
//  public static String convertMultipartFileToBase64(MultipartFile file) throws Exception {
//    if (file == null) {
//      return null;
//    }
//
//    return Base64.getEncoder().encodeToString(file.getBytes());
//  }
//
//  /**
//   * Convert string between two date format.
//   *
//   * @param dateInput the date input
//   * @param inputFormat the input format
//   * @param outputFormat the output format
//   * @return the string
//   */
//  public static String convertStringBetweenTwoDateFormat(String dateInput, String inputFormat,
//      String outputFormat) {
//    try {
//      Date ins = new SimpleDateFormat(inputFormat).parse(dateInput);
//      return new SimpleDateFormat(outputFormat).format(ins);
//    } catch (Exception e) {
//      return null;
//    }
//  }
//
//  /**
//   * Convert string to date time.
//   *
//   * @param dateStr the date str
//   * @param format the format
//   * @return the date
//   */
//  public static Date convertStringToDateTime(String dateStr, String format) {
//    try {
//      return new SimpleDateFormat(format).parse(dateStr);
//    } catch (Exception e) {
//      return null;
//    }
//  }
//
//  /**
//   * Convert string to GMT date time.
//   *
//   * @param dateInput the date input
//   * @param timeZoneId the time zone ID
//   * @param inputFormat the input format
//   * @param outputFormat the output format
//   * @return the string
//   * @throws Exception the exception
//   */
//  public static String convertStringToGMTDateTime(String dateInput, String timeZoneId,
//      String inputFormat, String outputFormat) throws Exception {
//    String outputDate = null;
//    try {
//      Date ins = new SimpleDateFormat(inputFormat).parse(dateInput);
//      ZonedDateTime date = ZonedDateTime.ofInstant(ins.toInstant(), ZoneId.of(timeZoneId));
//      outputDate = date.format(DateTimeFormatter.ofPattern(outputFormat));
//    } catch (Exception e) {
//      return null;
//    }
//    return outputDate;
//  }
//
//  /**
//   * Convert string to instant date.
//   *
//   * @param dateInput the date input
//   * @param format the format
//   * @return the instant
//   * @throws Exception the exception
//   */
//  public static Instant convertStringToInstantDate(String dateInput, String format)
//      throws Exception {
//    Instant date = null;
//    try {
//      SimpleDateFormat formatter = new SimpleDateFormat(format);
//      formatter.setLenient(false);
//      date = formatter.parse(dateInput).toInstant();
//    } catch (Exception e) {
//      return null;
//    }
//    return date;
//  }
//
//  /**
//   * Convert time to string.
//   *
//   * @param dateInput the date input
//   * @param format the format
//   * @return the string
//   * @throws Exception the exception
//   */
//  public static String convertTimeToString(String dateInput, String format) throws Exception {
//    String date = null;
//    if (StringUtils.isNumeric(dateInput)) {
//      date = convertUnixTimeToString(Long.valueOf(dateInput), format, null);
//    } else {
//      Instant instant = convertStringToInstantDate(dateInput, format);
//      if (instant != null) {
//        date = instant.toString();
//      }
//    }
//
//    return date;
//  }
//
//  /**
//   * Convert time to unix time.
//   *
//   * @param dateInput the date input
//   * @param format the format
//   * @return the long
//   * @throws Exception the exception
//   */
//  public static Long convertTimeToUnixTime(String dateInput, String format) throws Exception {
//    Long date = null;
//    if (StringUtils.isNumeric(dateInput)) {
//      date = Long.valueOf(dateInput);
//    } else {
//      Instant dateTime = convertStringToInstantDate(dateInput, format);
//      if (dateTime != null) {
//        date = dateTime.getEpochSecond();
//      }
//    }
//
//    return date;
//  }
//
//  /**
//   * Convert unix time to instant date.
//   *
//   * @param dateInput the date input
//   * @return the instant
//   * @throws Exception the exception
//   */
//  public static Instant convertUnixTimeToInstantDate(Long dateInput) throws Exception {
//    try {
//      return Instant.ofEpochSecond(dateInput);
//    } catch (Exception e) {
//      return null;
//    }
//  }
//
//  /**
//   * Convert unix time to string.
//   *
//   * @param dateInput the date input
//   * @param format the format
//   * @return the string
//   * @throws Exception the exception
//   */
//  public static String convertUnixTimeToString(Instant dateInput, String format) throws Exception {
//    return convertUnixTimeToString(dateInput.getEpochSecond(), format, null);
//  }
//
//  /**
//   * Convert unix time to string.
//   *
//   * @param dateInput the date input
//   * @param format the format
//   * @return the string
//   * @throws Exception the exception
//   */
//  public static String convertUnixTimeToString(Long dateInput, String format) throws Exception {
//    return convertUnixTimeToString(dateInput, format, null);
//  }
//
//  /**
//   * Convert unix time to string.
//   *
//   * @param dateInput the date input
//   * @param format the format
//   * @param timeZone the time zone
//   * @return the string
//   * @throws Exception the exception
//   */
//  public static String convertUnixTimeToString(Long dateInput, String format, String timeZone)
//      throws Exception {
//    String date = null;
//    try {
//      SimpleDateFormat formatter = new SimpleDateFormat(format);
//      if (timeZone != null) {
//        formatter.setTimeZone(TimeZone.getTimeZone(timeZone));
//      } else {
//        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
//      }
//      date = formatter.format(dateInput * 1000);
//    } catch (Exception e) {
//      return null;
//    }
//    return date;
//  }
//
//  /**
//   * Delete file.
//   *
//   * @param file the file
//   */
//  public static void deleteFile(File file) {
//    if (file == null) {
//      return;
//    }
//
//    if (file.exists()) {
//      file.delete();
//    }
//  }
//
//  /**
//   * Detect media type.
//   *
//   * @param data the data
//   * @return the string
//   */
//  public static String detectMediaType(byte[] data) {
//    try (TikaInputStream tikaInputStream = TikaInputStream.get(data)) {
//      return detectMediaTypeOfTikaInputStream(tikaInputStream);
//    } catch (IOException e) {
//      return null;
//    }
//  }
//
//  /**
//   * Detect media type.
//   *
//   * @param file the file
//   * @return the string
//   */
//  public static String detectMediaType(File file) {
//    Path path = FileSystems.getDefault().getPath(file.getAbsolutePath());
//    return detectMediaType(path);
//  }
//
//  /**
//   * Detect media type.
//   *
//   * @param inputStream the input stream
//   * @return the string
//   */
//  public static String detectMediaType(InputStream inputStream) {
//    try (TemporaryResources tmp = new TemporaryResources()) {
//      TikaInputStream tikaInputStream = TikaInputStream.get(inputStream, tmp);
//      return detectMediaTypeOfTikaInputStream(tikaInputStream);
//    } catch (IOException e) {
//      return null;
//    }
//  }
//
//  /**
//   * Detect media type.
//   *
//   * @param path the path
//   * @return the string
//   */
//  public static String detectMediaType(Path path) {
//    try (TikaInputStream tikaInputStream = TikaInputStream.get(path)) {
//      return detectMediaTypeOfTikaInputStream(tikaInputStream);
//    } catch (IOException e) {
//      return null;
//    }
//  }
//
//  /**
//   * Escape special characters SQL like.
//   *
//   * @param sqlLikeParameter the SQL like parameter
//   * @return the string
//   */
//  public static final String escapeSpecialCharactersSqlLike(String sqlLikeParameter) {
//    return StringUtils.isNotEmpty(sqlLikeParameter)
//        ? sqlLikeParameter.replace("%", "\\%").replace("_", "\\_")
//        : null;
//  }
//
//  /**
//   * Format double.
//   *
//   * @param value the value
//   * @return the double
//   */
//  public static Double formatDouble(Double value) {
//    if (value == null) {
//      return 0D;
//    }
//    return Math.round(value * 100.0) / 100.0;
//  }
//
//  /**
//   * Format user ID roles.
//   *
//   * @param role the role
//   * @param userId the user ID
//   * @return the string
//   */
//  public static String formatUserIdRoles(RoleType role, Integer userId) {
//    return role.getValue().toString() + COLON + userId.toString();
//  }
//
//  /**
//   * Gets the authorities from combined text.
//   *
//   * @param text the text
//   * @return the authorities from combined text
//   */
//  public static Set<UserAuthority> getAuthoritiesFromCombinedText(String text) {
//    Set<UserAuthority> authorities = new HashSet<>();
//    if (StringUtils.isNotEmpty(text)) {
//      String[] authoritiesArray = text.split(AUTHORITIES_SEPARATOR);
//      for (String element : authoritiesArray) {
//        authorities.add(new UserAuthority(element));
//      }
//    }
//    return authorities;
//  }
//
//  /**
//   * Gets the byte array from hex string.
//   *
//   * @param string the string
//   * @return the byte array from hex string
//   */
//  public static byte[] getByteArrayFromHexString(String string) {
//    if (string == null) {
//      return null;
//    }
//    return DatatypeConverter.parseHexBinary(string);
//  }
//
//  /**
//   * Gets the calendar from unix time.
//   *
//   * @param unixTime the unix time
//   * @return the calendar from unix time
//   */
//  public static Calendar getCalendarFromUnixTime(Long unixTime) {
//    Calendar cal = Calendar.getInstance();
//    cal.setTimeInMillis(unixTime * 1000);
//    return cal;
//  }
//
//  /**
//   * Gets the card last four digits.
//   *
//   * @param cardDigits the card digits
//   * @return the card last four digits
//   */
//  public static String getCardLastFourDigits(String cardDigits) {
//    return cardDigits.substring(cardDigits.length() - 4, cardDigits.length());
//  }
//
//  /**
//   * Gets the COCOS date time string from instant.
//   *
//   * @param instant the instant
//   * @return the COCOS date time string from instant
//   */
//  public static String getCocosDateTimeStringFromInstant(Instant instant) {
//    return getCocosDateTimeStringFromInstant(instant, ZoneOffset.UTC);
//  }
//
//  /**
//   * Gets the COCOS date time string from instant.
//   *
//   * @param instant the instant
//   * @param zoneOffset the zone offset
//   * @return the COCOS date time string from instant
//   */
//  public static String getCocosDateTimeStringFromInstant(Instant instant, ZoneOffset zoneOffset) {
//    try {
//      return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(instant.atOffset(zoneOffset));
//    } catch (Exception e) {
//      return null;
//    }
//  }
//
//  /**
//   * Gets the combined text from authorities.
//   *
//   * @param authorities the authorities
//   * @return the combined text from authorities
//   */
//  public static String getCombinedTextFromAuthorities(Collection<UserAuthority> authorities) {
//    String text = null;
//    if (CollectionUtils.isNotEmpty(authorities)) {
//      text = authorities.stream()
//          .map(UserAuthority::getAuthority)
//          .collect(Collectors.joining(AUTHORITIES_SEPARATOR));
//    }
//    return text;
//  }
//
//  /**
//   * Gets the dates between.
//   *
//   * @param startDate the start date
//   * @param endDate the end date
//   * @return the dates between
//   * @throws Exception the exception
//   */
//  public static List<Instant> getDatesBetween(Instant startDate, Instant endDate) throws Exception {
//    List<Instant> datesInRange = new ArrayList<>();
//    datesInRange.add(startDate);
//    Instant tmp = startDate.plus(1, ChronoUnit.DAYS);
//    while (tmp.isAfter(startDate) && tmp.isBefore(endDate)) {
//      datesInRange.add(tmp);
//      tmp = tmp.plus(1, ChronoUnit.DAYS);
//    }
//    String startDateStr =
//        convertUnixTimeToString(startDate, Constants.FORMAT.DATE_FORMAT_DD_MM_YYYY);
//    String endDateStr = convertUnixTimeToString(endDate, Constants.FORMAT.DATE_FORMAT_DD_MM_YYYY);
//
//    if (startDateStr != null && endDateStr != null && !startDateStr.equals(endDateStr)) {
//      datesInRange.add(endDate);
//    }
//    return datesInRange;
//  }
//
//  /**
//   * Gets the day of week.
//   *
//   * @param date the date
//   * @return the day of week
//   */
//  public static DayOfWeek getDayOfWeek(Instant date) {
//    return date.atOffset(ZoneOffset.UTC).getDayOfWeek();
//  }
//
//  /**
//   * Gets the day of week.
//   *
//   * @param epochSecond the epoch second
//   * @return the day of week
//   */
//  public static DayOfWeek getDayOfWeek(long epochSecond) {
//    return Instant.ofEpochSecond(epochSecond).atOffset(ZoneOffset.UTC).getDayOfWeek();
//  }
//
//  /**
//   * Gets the file extension.
//   *
//   * @param file the file
//   * @return the file extension
//   */
//  public static String getFileExtension(MultipartFile file) {
//    if (file == null) {
//      return null;
//    }
//
//    return FilenameUtils.getExtension(file.getOriginalFilename());
//  }
//
//  /**
//   * Gets the hex string from byte array.
//   *
//   * @param bytes the bytes
//   * @return the hex string from byte array
//   */
//  public static String getHexStringFromByteArray(byte[] bytes) {
//    if (bytes == null) {
//      return null;
//    }
//    int length = bytes.length;
//    byte[] hex = new byte[length << 1];
//    int i = length;
//    int j = i << 1;
//    byte b;
//    while (i > 0) {
//      b = bytes[--i];
//      hex[--j] = BYTE_TO_HEX_ARRAY[b & 0x0F];
//      hex[--j] = BYTE_TO_HEX_ARRAY[(b & 0xF0) >> 4];
//    }
//    return new String(hex, StandardCharsets.UTF_8);
//  }
//
//  /**
//   * Gets the hex string from string.
//   *
//   * @param string the string
//   * @return the hex string from string
//   */
//  public static String getHexStringFromString(String string) {
//    if (string == null) {
//      return null;
//    }
//    return getHexStringFromByteArray(string.getBytes(StandardCharsets.UTF_8));
//  }
//
//  /**
//   * Gets the host ip.
//   *
//   * @return the host ip
//   */
//  public static String getHostIp() {
//    if (hostIp != null) {
//      return hostIp;
//    }
//
//    String ip = getHostIpOfAwsEcsContainer();
//    if (ip != null) {
//      hostIp = ip;
//    }
//
//    if (hostIp == null) {
//      ip = getHostIpOfLocal();
//      if (ip != null) {
//        hostIp = ip;
//      }
//    }
//
//    return hostIp;
//  }
//
//  /**
//   * Gets the image S 3 link.
//   *
//   * @param s3Utils the s 3 utils
//   * @param imgS3Key the img S 3 key
//   * @param expireInMiliseconds the expire in miliseconds
//   * @return the image S 3 link
//   */
//  public static String getImageS3Link(S3Utils s3Utils, String imgS3Key, Long expireInMiliseconds) {
//    if (StringUtils.isNotEmpty(imgS3Key) && s3Utils != null) {
//      if (expireInMiliseconds == null) {
//        return s3Utils.generatePresignedUrlForGet(imgS3Key).toString();
//      } else {
//        return s3Utils.generatePresignedUrlForGet(imgS3Key, expireInMiliseconds).toString();
//      }
//    }
//
//    return null;
//  }
//
//  /**
//   * Gets the instant from COCOS date time.
//   *
//   * @param cocosDateTime the COCOS date time
//   * @return the instant from COCOS date time
//   */
//  public static Instant getInstantFromCocosDateTime(String cocosDateTime) {
//    return getInstantFromCocosDateTime(cocosDateTime, ZoneOffset.UTC);
//  }
//
//  /**
//   * Gets the instant from COCOS date time.
//   *
//   * @param cocosDateTime the COCOS date time
//   * @param zoneOffset the zone offset
//   * @return the instant from COCOS date time
//   */
//  public static Instant getInstantFromCocosDateTime(String cocosDateTime, ZoneOffset zoneOffset) {
//    try {
//      return LocalDateTime.parse(cocosDateTime).toInstant(zoneOffset);
//    } catch (Exception e) {
//      return null;
//    }
//  }
//
//  /**
//   * Gets the instant from COCOS utc date time.
//   *
//   * @param cocosUtcDateTime the COCOS utc date time
//   * @return the instant from COCOS utc date time
//   */
//  public static Instant getInstantFromCocosUtcDateTime(String cocosUtcDateTime) {
//    Instant instant = null;
//    try {
//      instant = Instant.parse(cocosUtcDateTime);
//    } catch (Exception e) {
//      return null;
//    }
//
//    return instant;
//  }
//
//  /**
//   * Gets the instant from string.
//   *
//   * @param string the string
//   * @return the instant from string
//   */
//  public static Instant getInstantFromString(String string) {
//    if (StringUtils.isEmpty(string)) {
//      return null;
//    }
//    return Instant.parse(string);
//  }
//
//  /**
//   * Gets the integer from string.
//   *
//   * @param text the text
//   * @return the integer from string
//   */
//  public static Integer getIntegerFromString(String text) {
//    if (text == null) {
//      return null;
//    }
//    return Integer.valueOf(text);
//  }
//
//  /**
//   * Gets the login user ID.
//   *
//   * @return the login user ID
//   */
//  public static Integer getLoginUserId() {
//    try {
//      UserPrincipal userPrincipal = getUserPrincipal();
//      if (userPrincipal != null) {
//        return userPrincipal.getUserId().intValue();
//      }
//      return null;
//    } catch (Exception e) {
//      return null;
//    }
//  }
//
//  /**
//   * Gets the login user name.
//   *
//   * @return the login user name
//   */
//  public static String getLoginUsername() {
//    try {
//      UserPrincipal userPrincipal = getUserPrincipal();
//      if (userPrincipal != null) {
//        return userPrincipal.getUsername();
//      }
//      return null;
//    } catch (Exception e) {
//      return null;
//    }
//  }
//
//  /**
//   * Gets the log stack trace.
//   *
//   * @param exception the exception
//   * @return the log stack trace
//   */
//  public static String getLogStackTrace(Exception exception) {
//    StringBuilder sb = new StringBuilder();
//
//    String message = ExceptionUtils.getMessage(exception);
//    if (StringUtils.isNotBlank(message)) {
//      sb.append(StringUtils.LF)
//          .append(ExceptionUtils.getMessage(exception));
//    }
//
//    Throwable cause = exception.getCause();
//    if (cause != null && !Objects.equals(exception, cause)) {
//      sb.append(StringUtils.LF)
//          .append("Cause: ")
//          .append(ExceptionUtils.getMessage(cause));
//      if (cause instanceof RestClientResponseException) {
//        RestClientResponseException restClientResponseExceptionCause =
//            (RestClientResponseException) cause;
//        sb.append(StringUtils.LF)
//            .append("Cause response body: ")
//            .append(restClientResponseExceptionCause.getResponseBodyAsString());
//      }
//    }
//
//    Throwable rootCause = ExceptionUtils.getRootCause(exception);
//    if (rootCause != null && !Objects.equals(cause, rootCause)
//        && !Objects.equals(exception, rootCause)) {
//      sb.append(StringUtils.LF)
//          .append("Root cause: ")
//          .append(ExceptionUtils.getMessage(rootCause));
//      if (rootCause instanceof RestClientResponseException) {
//        RestClientResponseException restClientResponseExceptionRootCause =
//            (RestClientResponseException) rootCause;
//        sb.append(StringUtils.LF)
//            .append("Root cause response body: ")
//            .append(restClientResponseExceptionRootCause.getResponseBodyAsString());
//      }
//    }
//
//    if (exception instanceof RestClientResponseException) {
//      RestClientResponseException restClientResponseException =
//          (RestClientResponseException) exception;
//      sb.append(StringUtils.LF)
//          .append("Response body: ")
//          .append(restClientResponseException.getResponseBodyAsString());
//    }
//
//    sb.append(StringUtils.LF)
//        .append(ExceptionUtils.getStackTrace(exception));
//
//    return sb.toString();
//  }
//
//  /**
//   * Gets the random transaction ref.
//   *
//   * @return the random transaction ref
//   */
//  public static String getRandomTransactionRef() {
//    StringBuilder sb = new StringBuilder();
//    sb.append(PaymentConstants.TRANSACTION_REF_PREFIX);
//    sb.append(UUID.randomUUID());
//    return sb.toString();
//  }
//
//  /**
//   * Gets the request client ip.
//   *
//   * @return the request client ip
//   */
//  public static String getRequestClientIp() {
//    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//    if (requestAttributes == null) {
//      return StringUtils.EMPTY;
//    }
//    HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
//    for (String requestIpHeader : REQUEST_IP_HEADERS) {
//      String ipList = request.getHeader(requestIpHeader);
//      if (StringUtils.isNotBlank(ipList)
//          && !StringUtils.equalsAnyIgnoreCase(ipList, "unknown", "null")) {
//        // return ipList.split(COMMA)[0];
//        return ipList;
//      }
//    }
//
//    return request.getRemoteAddr();
//  }
//
//  /**
//   * Gets the request info.
//   *
//   * @return the request info
//   */
//  public static String getRequestInfo() {
//    // client IP = clientIp, host IP = hostIp, method = method, URI = uri
//    StringBuilder sb = new StringBuilder();
//    sb.append("client IP = ").append(CommonUtils.getRequestClientIp());
//    if (CommonUtils.getHostIp() != null) {
//      sb.append(", host IP = ").append(CommonUtils.getHostIp());
//    }
//    sb.append(", method = ").append(CommonUtils.getRequestMethod());
//    sb.append(", URI = ").append(CommonUtils.getRequestUri());
//    return sb.toString();
//  }
//
//  /**
//   * Gets the request info string.
//   *
//   * @return the request info string
//   */
//  public static String getRequestInfoString() {
//    // [clientIp] => [hostIp] method uri
//    StringBuilder sb = new StringBuilder();
//    sb.append("[").append(CommonUtils.getRequestClientIp()).append("]");
//    if (CommonUtils.getHostIp() != null) {
//      sb.append(" => [").append(CommonUtils.getHostIp()).append("]");
//    }
//    sb.append(StringUtils.SPACE).append(CommonUtils.getRequestMethod());
//    sb.append(StringUtils.SPACE).append(CommonUtils.getRequestUri());
//    return sb.toString();
//  }
//
//  /**
//   * Gets the request method.
//   *
//   * @return the request method
//   */
//  public static String getRequestMethod() {
//    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//    if (requestAttributes == null) {
//      return StringUtils.EMPTY;
//    }
//    HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
//    return request.getMethod();
//  }
//
//  /**
//   * Gets the request URI.
//   *
//   * @return the request URI
//   */
//  public static String getRequestUri() {
//    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//    if (requestAttributes == null) {
//      return StringUtils.EMPTY;
//    }
//    HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
//    return request.getRequestURI();
//  }
//
//  /**
//   * Gets the singapore time now.
//   *
//   * @return the singapore time now
//   * @throws Exception the exception
//   */
//  public static String getSingaporeTimeNow() throws Exception {
//    String timeZoneSingapore = "Asia/Singapore";
//
//    return convertUnixTimeToString(ZonedDateTime.now().toEpochSecond(),
//        Constants.FORMAT.DATE_FORMAT_YYYYMMDDHHMMSS, timeZoneSingapore);
//  }
//
//  /**
//   * Gets the string from hex string.
//   *
//   * @param string the string
//   * @return the string from hex string
//   */
//  public static String getStringFromHexString(String string) {
//    if (string == null) {
//      return null;
//    }
//    return new String(getByteArrayFromHexString(string), StandardCharsets.UTF_8);
//  }
//
//  /**
//   * Gets the string from instant.
//   *
//   * @param instant the instant
//   * @return the string from instant
//   */
//  public static String getStringFromInstant(Instant instant) {
//    if (instant == null) {
//      return null;
//    }
//    return instant.toString();
//  }
//
//  /**
//   * Gets the user mobile no.
//   *
//   * @param user the user
//   * @return the user mobile no
//   */
//  public static String getUserMobileNo(UserDto user) {
//    if (user == null) {
//      return "";
//    }
//
//    return validatePhoneNumber(user.getUsername()) ? user.getUsername() : user.getMobileNo();
//  }
//
//  /**
//   * Gets the user principal.
//   *
//   * @return the user principal
//   */
//  public static UserPrincipal getUserPrincipal() {
//    try {
//      UserAuthenticationToken userAuthToken =
//          (UserAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//      return (UserPrincipal) userAuthToken.getPrincipal();
//    } catch (Exception e) {
//      return null;
//    }
//  }
//
//  /**
//   * Gets the zone offset.
//   *
//   * @return the zone offset
//   */
//  public static ZoneOffset getZoneOffset() {
//    if (zoneOffset != null) {
//      return zoneOffset;
//    }
//
//    zoneOffset = OffsetDateTime.now().getOffset();
//
//    return zoneOffset;
//  }
//
//  /**
//   * Checks if is between two date.
//   *
//   * @param dateCompare the date compare
//   * @param min the min
//   * @param max the max
//   * @return true, if is between two date
//   */
//  public static boolean isBetweenTwoDate(Date dateCompare, Date min, Date max) {
//    return isBetweenTwoDate(dateCompare.toInstant(), min.toInstant(), max.toInstant());
//  }
//
//  /**
//   * Checks if is between two date.
//   *
//   * @param dateCompare the date compare
//   * @param min the min
//   * @param max the max
//   * @return true, if is between two date
//   */
//  public static boolean isBetweenTwoDate(Instant dateCompare, Instant min, Instant max) {
//    return dateCompare.compareTo(min) >= 0 && dateCompare.compareTo(max) <= 0;
//  }
//
//  /**
//   * Checks if is deadlock error.
//   *
//   * @param e the e
//   * @return true, if is deadlock error
//   */
//  public static boolean isDeadlockError(Exception e) {
//    return isDeadlockSqlException(e.getCause())
//        || isDeadlockSqlException(ExceptionUtils.getRootCause(e));
//  }
//
//  /**
//   * Checks if is deadlock SQL exception.
//   *
//   * @param e the e
//   * @return true, if is deadlock SQL exception
//   */
//  public static boolean isDeadlockSqlException(Throwable e) {
//    if (!(e instanceof SQLException)) {
//      return false;
//    }
//
//    return StringUtils.startsWithAny(ExceptionUtils.getMessage(e),
//        "PSQLException: ERROR: deadlock detected");
//  }
//
//  /**
//   * Checks if is duplicate key value error.
//   *
//   * @param e the e
//   * @return true, if is duplicate key value error
//   */
//  public static boolean isDuplicateKeyValueError(Exception e) {
//    return isDuplicateKeyValueSqlException(e.getCause())
//        || isDuplicateKeyValueSqlException(ExceptionUtils.getRootCause(e));
//  }
//
//  /**
//   * Checks if is duplicate key value SQL exception.
//   *
//   * @param e the e
//   * @return true, if is duplicate key value SQL exception
//   */
//  public static boolean isDuplicateKeyValueSqlException(Throwable e) {
//    if (!(e instanceof SQLException)) {
//      return false;
//    }
//
//    return StringUtils.startsWithAny(ExceptionUtils.getMessage(e),
//        "PSQLException: ERROR: duplicate key value violates unique constraint",
//        "JdbcSQLException: Unique index or primary key violation");
//  }
//
//  /**
//   * Checks if is overlapping.
//   *
//   * @param start1 the start 1
//   * @param end1 the end 1
//   * @param start2 the start 2
//   * @param end2 the end 2
//   * @return true, if is overlapping
//   */
//  public static boolean isOverlapping(Instant start1, Instant end1, Instant start2, Instant end2) {
//    return (start1.compareTo(end2) <= 0) && (end1.compareTo(start2) >= 0);
//  }
//
//  /**
//   * Checks if is valid email.
//   *
//   * @param email the email
//   * @return true, if is valid email
//   */
//  public static boolean isValidEmail(String email) {
//    String regex =
//        "^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])$";
//    return email.toLowerCase().matches(regex);
//  }
//
//  /**
//   * Checks if is valid image file.
//   *
//   * @param file the file
//   * @param maxFileSize the max file size
//   * @return true, if is valid image file
//   */
//  public static boolean isValidImageFile(MultipartFile file, Integer maxFileSize) {
//    try {
//      Double imageSize = file.getSize() / (1024D * 1024D);
//      return isValidEmail(file.getContentType())
//          && imageSize <= maxFileSize;
//    } catch (Exception e) {
//      return false;
//    }
//  }
//
//  /**
//   * Checks if is valid image file.
//   *
//   * @param contentType the content type
//   * @return true, if is valid image file
//   */
//  public static boolean isValidImageFile(String contentType) {
//    return (contentType.equals("image/pjpeg") || contentType.equals("image/jpeg")
//        || contentType.equals("image/png") || contentType.equals("image/gif")
//        || contentType.equals("image/bmp") || contentType.equals("image/x-png")
//        || contentType.equals("image/x-icon"));
//  }
//
//  /**
//   * Checks if is valid URL.
//   *
//   * @param url the URL
//   * @return true, if is valid URL
//   */
//  public static boolean isValidURL(String url) {
//    try {
//      new URL(url).toURI();
//    } catch (MalformedURLException | URISyntaxException e) {
//      return false;
//    }
//    return true;
//  }
//
//  /**
//   * Join list by comma.
//   *
//   * @param <E> the element type
//   * @param list the list
//   * @return the string
//   */
//  public static <E> String joinListByComma(List<E> list) {
//    return joinListByComma(list, "<null>");
//  }
//
//  /**
//   * Join list by comma.
//   *
//   * @param <E> the element type
//   * @param list the list
//   * @param nullValue the null value
//   * @return the string
//   */
//  public static <E> String joinListByComma(List<E> list, String nullValue) {
//    if (list == null) {
//      return null;
//    }
//    return list.stream().map(e -> (e == null) ? nullValue : e.toString())
//        .collect(Collectors.joining(COMMA));
//  }
//
//  /**
//   * Log rest API custom.
//   *
//   * @param logger the logger
//   * @param msg the msg
//   * @param params the params
//   */
//  public static void logRestApiCustom(Logger logger, String msg, Object... params) {
//    if (!logger.isDebugEnabled()) {
//      return;
//    }
//    logger.debug(getRequestInfoString() + StringUtils.SPACE + msg, params);
//  }
//
//  /**
//   * Log rest API debug.
//   *
//   * @param logger the logger
//   * @param msg the msg
//   * @param params the params
//   */
//  public static void logRestApiDebug(Logger logger, String msg, Object... params) {
//    if (!logger.isDebugEnabled()) {
//      return;
//    }
//    logger.debug(getRequestInfoString() + StringUtils.SPACE + msg, params);
//  }
//
//  /**
//   * Log rest API end.
//   *
//   * @param logger the logger
//   */
//  public static void logRestApiEnd(Logger logger) {
//    if (!logger.isInfoEnabled()) {
//      return;
//    }
//    logger.info("{} END", getRequestInfoString());
//  }
//
//  /**
//   * Log rest API error.
//   *
//   * @param logger the logger
//   * @param msg the msg
//   * @param params the params
//   */
//  public static void logRestApiError(Logger logger, String msg, Object... params) {
//    if (!logger.isErrorEnabled()) {
//      return;
//    }
//    logger.error(getRequestInfoString() + StringUtils.SPACE + msg, params);
//  }
//
//  /**
//   * Log rest API exception.
//   *
//   * @param logger the logger
//   * @param exception the exception
//   */
//  public static void logRestApiException(Logger logger, Exception exception) {
//    logRestApiException(logger, null, exception);
//  }
//
//  /**
//   * Log rest API exception.
//   *
//   * @param logger the logger
//   * @param message the message
//   * @param exception the exception
//   */
//  public static void logRestApiException(Logger logger, String message, Exception exception) {
//    logRestApiExceptionWarn(logger, message, exception);
//  }
//
//  /**
//   * Log rest API exception debug.
//   *
//   * @param logger the logger
//   * @param exception the exception
//   */
//  public static void logRestApiExceptionDebug(Logger logger, Exception exception) {
//    logRestApiExceptionDebug(logger, null, exception);
//  }
//
//  /**
//   * Log rest API exception debug.
//   *
//   * @param logger the logger
//   * @param message the message
//   * @param exception the exception
//   */
//  public static void logRestApiExceptionDebug(Logger logger, String message, Exception exception) {
//    if (!logger.isDebugEnabled()) {
//      return;
//    }
//    logger.debug(createMessageForExceptionLog(message), getLogStackTrace(exception));
//  }
//
//  /**
//   * Log rest API exception error.
//   *
//   * @param logger the logger
//   * @param exception the exception
//   */
//  public static void logRestApiExceptionError(Logger logger, Exception exception) {
//    logRestApiExceptionError(logger, null, exception);
//  }
//
//  /**
//   * Log rest API exception error.
//   *
//   * @param logger the logger
//   * @param message the message
//   * @param exception the exception
//   */
//  public static void logRestApiExceptionError(Logger logger, String message, Exception exception) {
//    if (!logger.isErrorEnabled()) {
//      return;
//    }
//    logger.error(createMessageForExceptionLog(message), getLogStackTrace(exception));
//  }
//
//  /**
//   * Log rest API exception fatal.
//   *
//   * @param logger the logger
//   * @param exception the exception
//   */
//  public static void logRestApiExceptionFatal(Logger logger, Exception exception) {
//    logRestApiExceptionFatal(logger, null, exception);
//  }
//
//  /**
//   * Log rest API exception fatal.
//   *
//   * @param logger the logger
//   * @param message the message
//   * @param exception the exception
//   */
//  public static void logRestApiExceptionFatal(Logger logger, String message, Exception exception) {
//    if (!logger.isFatalEnabled()) {
//      return;
//    }
//    logger.fatal(createMessageForExceptionLog(message), getLogStackTrace(exception));
//  }
//
//  /**
//   * Log rest API exception info.
//   *
//   * @param logger the logger
//   * @param exception the exception
//   */
//  public static void logRestApiExceptionInfo(Logger logger, Exception exception) {
//    logRestApiExceptionInfo(logger, null, exception);
//  }
//
//  /**
//   * Log rest API exception info.
//   *
//   * @param logger the logger
//   * @param message the message
//   * @param exception the exception
//   */
//  public static void logRestApiExceptionInfo(Logger logger, String message, Exception exception) {
//    if (!logger.isInfoEnabled()) {
//      return;
//    }
//    logger.info(createMessageForExceptionLog(message), getLogStackTrace(exception));
//  }
//
//  /**
//   * Log rest API exception trace.
//   *
//   * @param logger the logger
//   * @param exception the exception
//   */
//  public static void logRestApiExceptionTrace(Logger logger, Exception exception) {
//    logRestApiExceptionTrace(logger, null, exception);
//  }
//
//  /**
//   * Log rest API exception trace.
//   *
//   * @param logger the logger
//   * @param message the message
//   * @param exception the exception
//   */
//  public static void logRestApiExceptionTrace(Logger logger, String message, Exception exception) {
//    if (!logger.isTraceEnabled()) {
//      return;
//    }
//    logger.trace(createMessageForExceptionLog(message), getLogStackTrace(exception));
//  }
//
//  /**
//   * Log rest API exception warn.
//   *
//   * @param logger the logger
//   * @param exception the exception
//   */
//  public static void logRestApiExceptionWarn(Logger logger, Exception exception) {
//    logRestApiExceptionWarn(logger, null, exception);
//  }
//
//  /**
//   * Log rest API exception warn.
//   *
//   * @param logger the logger
//   * @param message the message
//   * @param exception the exception
//   */
//  public static void logRestApiExceptionWarn(Logger logger, String message, Exception exception) {
//    if (!logger.isWarnEnabled()) {
//      return;
//    }
//    logger.warn(createMessageForExceptionLog(message), getLogStackTrace(exception));
//  }
//
//  /**
//   * Log rest API fatal.
//   *
//   * @param logger the logger
//   * @param msg the msg
//   * @param params the params
//   */
//  public static void logRestApiFatal(Logger logger, String msg, Object... params) {
//    if (!logger.isFatalEnabled()) {
//      return;
//    }
//    logger.fatal(getRequestInfoString() + StringUtils.SPACE + msg, params);
//  }
//
//  /**
//   * Log rest API info.
//   *
//   * @param logger the logger
//   * @param msg the msg
//   * @param params the params
//   */
//  public static void logRestApiInfo(Logger logger, String msg, Object... params) {
//    if (!logger.isInfoEnabled()) {
//      return;
//    }
//    logger.info(getRequestInfoString() + StringUtils.SPACE + msg, params);
//  }
//
//  /**
//   * Log rest API request body.
//   *
//   * @param logger the logger
//   * @param requestBody the request body
//   */
//  public static void logRestApiRequestBody(Logger logger, Object requestBody) {
//    if (!logger.isDebugEnabled()) {
//      return;
//    }
//    logger.info("{} requestBody: {}", getRequestInfoString(), requestBody);
//  }
//
//  /**
//   * Log rest API response body.
//   *
//   * @param logger the logger
//   * @param responseBody the response body
//   */
//  public static void logRestApiResponseBody(Logger logger, Object responseBody) {
//    if (!logger.isDebugEnabled()) {
//      return;
//    }
//    logger.info("{} responseBody: {}", getRequestInfoString(), responseBody);
//  }
//
//  /**
//   * Log rest API start.
//   *
//   * @param logger the logger
//   */
//  public static void logRestApiStart(Logger logger) {
//    if (!logger.isInfoEnabled()) {
//      return;
//    }
//    logger.info("{} START", getRequestInfoString());
//  }
//
//  /**
//   * Log rest API trace.
//   *
//   * @param logger the logger
//   * @param msg the msg
//   * @param params the params
//   */
//  public static void logRestApiTrace(Logger logger, String msg, Object... params) {
//    if (!logger.isTraceEnabled()) {
//      return;
//    }
//    logger.trace(getRequestInfoString() + StringUtils.SPACE + msg, params);
//  }
//
//  /**
//   * Log rest API warn.
//   *
//   * @param logger the logger
//   * @param msg the msg
//   * @param params the params
//   */
//  public static void logRestApiWarn(Logger logger, String msg, Object... params) {
//    if (!logger.isWarnEnabled()) {
//      return;
//    }
//    logger.warn(getRequestInfoString() + StringUtils.SPACE + msg, params);
//  }
//
//  /**
//   * Minus instant by minutes.
//   *
//   * @param originTime the origin time
//   * @param minutes the minutes
//   * @return the instant
//   */
//  public static Instant minusInstantByMinutes(Instant originTime, int minutes) {
//    if (originTime == null) {
//      return null;
//    }
//    return originTime.minus(minutes, ChronoUnit.MINUTES);
//  }
//
//  /**
//   * Parses the string as instant.
//   *
//   * @param date the date
//   * @param initDateFormat the init date format
//   * @param endDateFormat the end date format
//   * @return the instant
//   * @throws Exception the exception
//   */
//  public static Instant parseStringAsInstant(String date, String initDateFormat,
//      String endDateFormat)
//      throws Exception {
//
//    try {
//      Date initDate = new SimpleDateFormat(initDateFormat).parse(date);
//      SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
//      String parsedDate = formatter.format(initDate);
//      return Instant.parse(parsedDate);
//    } catch (Exception e) {
//      return null;
//    }
//  }
//
//  /**
//   * Partition based on size.
//   *
//   * @param <T> the generic type
//   * @param inputList the input list
//   * @param size the size
//   * @return the collection
//   */
//  public static <T> Collection<List<T>> partitionBasedOnSize(List<T> inputList, int size) {
//    final AtomicInteger counter = new AtomicInteger(0);
//    return inputList.stream()
//        .collect(Collectors.groupingBy(s -> counter.getAndIncrement() / size))
//        .values();
//  }
//
//  /**
//   * Plus instant by minutes.
//   *
//   * @param originTime the origin time
//   * @param minutes the minutes
//   * @return the instant
//   */
//  public static Instant plusInstantByMinutes(Instant originTime, int minutes) {
//    if (originTime == null) {
//      return null;
//    }
//    return originTime.plus(minutes, ChronoUnit.MINUTES);
//  }
//
//  /**
//   * Reduce multipart file capacity base 64.
//   *
//   * @param file the file
//   * @return the string
//   * @throws Exception the exception
//   */
//  public static String reduceMultipartFileCapacityBase64(MultipartFile file) throws Exception {
//    if (file == null) {
//      return null;
//    }
//
//    // check size < 500KB
//    Float fileCapacityKB = (float) (file.getSize() / 1000);
//
//    if (fileCapacityKB < Constants.UPLOAD_INFO.MAX_IMG_CAPACITY) {
//      return convertMultipartFileToBase64(file);
//    }
//
//    BufferedImage image = ImageIO.read(file.getInputStream());
//    Float rate = Constants.UPLOAD_INFO.MAX_IMG_CAPACITY / fileCapacityKB;
//
//    int width = image.getWidth();
//    int height = image.getHeight();
//
//    // Get the pixels of the image to an int array
//    int[] pixels = image.getRGB(0, 0, width, height, null, 0, width);
//
//    // Create a new buffered image without an alpha channel. (TYPE_INT_RGB)
//    BufferedImage imageCopy = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//
//    // Set the pixels of the original image to the new image
//    imageCopy.setRGB(0, 0, width, height, pixels, 0, width);
//
//    ByteArrayOutputStream os = new ByteArrayOutputStream();
//    ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
//
//    ImageWriteParam param = writer.getDefaultWriteParam();
//    if (param.canWriteCompressed()) {
//      param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
//      param.setCompressionQuality(rate);
//    }
//
//    writer.setOutput(ImageIO.createImageOutputStream(os));
//    writer.write(null, new IIOImage(imageCopy, null, null), param);
//
//    os.close();
//    writer.dispose();
//
//    return Base64.getEncoder().encodeToString(os.toByteArray());
//  }
//
//  /**
//   * Resize image.
//   *
//   * @param originImage the origin image
//   * @param imageExtension the image extension
//   * @param scaledWidth the scaled width
//   * @param scaledHeight the scaled height
//   * @return the byte[]
//   * @throws IOException Signals that an I/O exception has occurred.
//   */
//  public static byte[] resizeImage(MultipartFile originImage, String imageExtension,
//      Integer scaledWidth, Integer scaledHeight) throws IOException {
//    // this method should be called after call isValidImageFile()
//    Objects.requireNonNull(scaledWidth, "Scaled width can not be null!");
//    final Logger logger = LogManager.getLogger();
//    logRestApiInfo(logger, "resize image with scaledWidth {} START", scaledWidth);
//    byte[] bytes = originImage.getBytes();
//    try (
//        InputStream is = new ByteArrayInputStream(bytes);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
//      BufferedImage inputImage = ImageIO.read(is);
//
//      if (inputImage.getWidth() <= scaledWidth) {
//        return bytes; // just return original image
//      }
//      if (scaledHeight == null) {
//        scaledHeight = inputImage.getHeight() * scaledWidth / inputImage.getWidth();
//      }
//      // output image
//      BufferedImage outputImage = new BufferedImage(scaledWidth,
//          scaledHeight, inputImage.getType());
//
//      // scales the input image to the output image
//      Graphics2D g2d = outputImage.createGraphics();
//      g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
//      g2d.dispose();
//
//      // convert BufferedImage to byte array
//      ImageIO.write(outputImage, imageExtension, baos);
//      return baos.toByteArray();
//    } catch (IOException e) {
//      logRestApiWarn(logger, "resizeImage error = {}", e);
//      throw e;
//    }
//  }
//
//  /**
//   * Should retry for method with exception.
//   *
//   * @param method the method
//   * @param throwable the throwable
//   * @return true, if successful
//   */
//  public static boolean shouldRetryForMethodWithException(HttpMethod method, Throwable throwable) {
//    if (throwable == null) {
//      return false;
//    }
//
//    Throwable rootCause = ExceptionUtils.getRootCause(throwable);
//    Throwable cause = throwable.getCause();
//
//    if (rootCause instanceof UnknownHostException
//        || cause instanceof UnknownHostException
//        || rootCause instanceof ConnectException
//        || cause instanceof ConnectException
//        || rootCause instanceof ServiceUnavailable
//        || cause instanceof ServiceUnavailable) {
//      return true;
//    }
//
//    if ((rootCause instanceof NoHttpResponseException
//        || cause instanceof NoHttpResponseException)
//        && Objects.equals(HttpMethod.GET, method)) {
//      return true;
//    }
//
//    if ((rootCause instanceof IllegalArgumentException
//        || cause instanceof IllegalArgumentException)
//        && Objects.equals(HttpMethod.GET, method)) {
//      return true;
//    }
//
//    return false;
//  }
//
//  /**
//   * Size of base 64 to KB.
//   *
//   * @param base64String the base 64 string
//   * @return the double
//   */
//  public static Double sizeOfBase64ToKB(String base64String) {
//    Double result = -1.0;
//    if (StringUtils.isNotEmpty(base64String)) {
//      Integer padding = 0;
//      if (base64String.endsWith("==")) {
//        padding = 2;
//      } else {
//        if (base64String.endsWith("=")) {
//          padding = 1;
//        }
//      }
//      result = (Math.ceil(base64String.length() / 4) * 3) - padding;
//    }
//    return result / 1000;
//  }
//
//  /**
//   * String contains item from list.
//   *
//   * @param inputStr the input str
//   * @param items the items
//   * @return true, if successful
//   */
//  public static boolean stringContainsItemFromList(String inputStr, String[] items) {
//    return Arrays.stream(items).parallel().anyMatch(inputStr::contains);
//  }
//
//  /**
//   * String to array.
//   *
//   * @param stringArray the string array
//   * @return the string[]
//   */
//  public static String[] stringToArray(String stringArray) {
//    return StringUtils.isNotBlank(stringArray) ? stringArray.split(COMMA)
//        : new String[0];
//  }
//
//  /**
//   * String to list.
//   *
//   * @param stringList the string list
//   * @return the list
//   */
//  public static List<String> stringToList(String stringList) {
//    return StringUtils.isNotBlank(stringList) ? Arrays.asList(stringList.split(COMMA))
//        : new ArrayList<>();
//  }
//
//  /**
//   * String to list integer.
//   *
//   * @param stringList the string list
//   * @return the list
//   */
//  public static List<Integer> stringToListInteger(String stringList) {
//    return StringUtils.isNotBlank(stringList)
//        ? Arrays.asList(stringList.split(COMMA)).stream().map(Integer::parseInt)
//            .collect(Collectors.toList())
//        : new ArrayList<>();
//  }
//
//  /**
//   * Throw exception.
//   *
//   * @param logger the logger
//   * @param e the e
//   * @param response the response
//   * @return the HTTP status
//   */
//  public static HttpStatus throwException(Logger logger, Exception e, BaseResponse response) {
//    if (e instanceof BaseException) {
//      BaseException baseException = (BaseException) e;
//      BaseStatusCode statusCode = baseException.getStatusCode();
//      CommonUtils.logRestApiException(logger, e);
//
//      if (statusCode != null) {
//        response.setStatusCode(statusCode.getStatusCode());
//        response.setMessage(statusCode.getMessage());
//        response.setFunctionCode(statusCode.getFunctionCode());
//      } else {
//        response.setStatusCode(400);
//        response.setMessage(e.getMessage());
//      }
//      response.setData(baseException.getErrorDetail());
//      return HttpStatus.BAD_REQUEST;
//    } else {
//      CommonUtils.logRestApiExceptionError(logger, e);
//
//      response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//      response.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
//      return HttpStatus.INTERNAL_SERVER_ERROR;
//    }
//  }
//
//  /**
//   * Time convert minutes to DDHHMM.
//   *
//   * @param times the times
//   * @return the string
//   */
//  public static String timeConvertMinutesToDDHHMM(Long times) {
//    if (times == null || times < 0) {
//      return "";
//    }
//
//    Long days = times / 24 / 60;
//    Long hours = times / 60 % 24;
//    Long minutes = times % 60;
//
//    StringBuilder sb = new StringBuilder().append(String.format("%02d", days)).append(":")
//        .append(String.format("%02d", hours)).append(":").append(String.format("%02d", minutes));
//
//    return sb.toString();
//  }
//
//  /**
//   * To camel case.
//   *
//   * @param s the s
//   * @return the string
//   */
//  public static String toCamelCase(String s) {
//    String[] parts = s.split("_");
//    String camelCaseString = "";
//    for (String part : parts) {
//      camelCaseString = camelCaseString + toProperCase(part);
//    }
//    return camelCaseString.substring(0, 1).toLowerCase() + camelCaseString.substring(1);
//  }
//
//  /**
//   * To string of big bytes.
//   *
//   * @param bigByte the big byte
//   * @return the string
//   */
//  public static String toStringOfBigBytes(byte[] bigByte) {
//    return (bigByte == null) ? "null" : ("Big bytes with length " + bigByte.length);
//  }
//
//  /**
//   * To string of big string.
//   *
//   * @param bigString the big string
//   * @return the string
//   */
//  public static String toStringOfBigString(String bigString) {
//    return (bigString == null) ? "null" : ("Big string with length " + bigString.length());
//  }
//
//  /**
//   * Upload image to amazon bucket.
//   *
//   * @param s3Utils the s 3 utils
//   * @param imageS3Key the image S 3 key
//   * @param image the image
//   * @param bucketType the bucket type
//   * @throws IOException Signals that an I/O exception has occurred.
//   */
//  public static void uploadImageToAmazonBucket(S3Utils s3Utils, String imageS3Key, byte[] image,
//      UploadImageBucketType bucketType) throws IOException {
//    switch (bucketType) {
//      case PUBLIC_BUCKET:
//        s3Utils.uploadBytesToBucketPublic(imageS3Key, image);
//        break;
//      case PRIVATE_BUCKET:
//        s3Utils.uploadBytes(imageS3Key, image);
//        break;
//      default:
//        s3Utils.uploadBytes(imageS3Key, image);
//        break;
//    }
//  }
//
//  /**
//   * Upload origin and resized image to AWS.
//   *
//   * @param s3Utils the s 3 utils
//   * @param imageS3Key the image S 3 key
//   * @param originImage the origin image
//   * @param bucketType the bucket type
//   * @throws IOException Signals that an I/O exception has occurred.
//   */
//  public static void uploadOriginAndResizedImageToAws(S3Utils s3Utils, String imageS3Key,
//      MultipartFile originImage, UploadImageBucketType bucketType) throws IOException {
//    final Logger logger = LogManager.getLogger();
//    logRestApiInfo(logger, "upload origin and resize image to S3 START");
//    logRestApiInfo(logger, "image key to process = {}", imageS3Key);
//    final String originImageS3Key = imageS3Key + Constants.IMAGE_PROCESS.ADDITION_ORIGIN_IMAGE_LINK;
//    final String largeImageS3Key = imageS3Key + Constants.IMAGE_PROCESS.ADDITION_LARGE_IMAGE_LINK;
//    String contentType = "";
//    String originalFilename = "";
//    if (originImage != null) {
//      contentType = originImage.getContentType();
//      originalFilename = originImage.getOriginalFilename();
//    }
//    if (originImage == null || !isValidImageFile(contentType)) { // case invalid image
//      throw new IllegalArgumentException(
//          "You provided an invalid image, please try with another image.");
//    }
//
//    // upload origin image
//    uploadImageToAmazonBucket(s3Utils, originImageS3Key, originImage.getBytes(), bucketType);
//
//    // try to process image
//    String imageExtension = originalFilename.substring(originalFilename.lastIndexOf(DOT) + 1);
//    byte[] byteArrSmallImage = null;
//    byte[] byteArrLargeImage = null;
//
//    CommonUtils.logRestApiInfo(logger, "resizeImage with smallWidth ={}, largeWidth ={}",
//        Constants.IMAGE_PROCESS.SMALL_WIDTH_VEHICLE_IMAGE,
//        Constants.IMAGE_PROCESS.LARGE_WIDTH_VEHICLE_IMAGE);
//    byteArrSmallImage = resizeImage(originImage, imageExtension,
//        Constants.IMAGE_PROCESS.SMALL_WIDTH_VEHICLE_IMAGE, null);
//    byteArrLargeImage = resizeImage(originImage, imageExtension,
//        Constants.IMAGE_PROCESS.LARGE_WIDTH_VEHICLE_IMAGE, null);
//
//    if (byteArrSmallImage != null) { // upload resized image
//      uploadImageToAmazonBucket(s3Utils, imageS3Key, byteArrSmallImage, bucketType);
//    }
//    if (byteArrLargeImage != null) {
//      uploadImageToAmazonBucket(s3Utils, largeImageS3Key, byteArrLargeImage, bucketType);
//    }
//    logRestApiInfo(logger, "upload origin and resize image to S3 DONE");
//  }
//
//  /**
//   * Validate phone number.
//   *
//   * @param input the input
//   * @return true, if successful
//   */
//  public static boolean validatePhoneNumber(String input) {
//    String regex = "^[+0-9-\\(\\)\\s]*{6,14}$";
//    return StringUtils.isNotEmpty(input) && input.matches(regex);
//  }
//
//  /**
//   * Creates the message for exception log.
//   *
//   * @param message the message
//   * @return the string
//   */
//  private static String createMessageForExceptionLog(String message) {
//    StringBuilder stringBuilder = new StringBuilder()
//        .append(getRequestInfoString());
//
//    if (StringUtils.isNotEmpty(message)) {
//      stringBuilder.append(StringUtils.SPACE);
//      stringBuilder.append(message);
//    }
//
//    stringBuilder.append(" {}");
//
//    return stringBuilder.toString();
//  }
//
//  /**
//   * Detect media type of tika input stream.
//   *
//   * @param tikaInputStream the tika input stream
//   * @return the string
//   */
//  private static String detectMediaTypeOfTikaInputStream(TikaInputStream tikaInputStream) {
//    TikaConfig tikaConfig = TikaConfig.getDefaultConfig();
//    Metadata metadata = new Metadata();
//    Detector detector = tikaConfig.getDetector();
//    MediaType mediaType;
//    try {
//      mediaType = detector.detect(tikaInputStream, metadata);
//    } catch (IOException e) {
//      return null;
//    }
//    // format: text/plain
//    return new StringBuilder()
//        .append(mediaType.getType())
//        .append(SLASH)
//        .append(mediaType.getSubtype())
//        .toString();
//  }
//
//  /**
//   * Gets the host ip of AWS ecs container.
//   *
//   * @return the host ip of AWS ecs container
//   */
//  @SuppressWarnings("unchecked")
//  private static String getHostIpOfAwsEcsContainer() {
//    try {
//      String ecsContainerMetadataUri = System.getenv("ECS_CONTAINER_METADATA_URI");
//      if (StringUtils.isBlank(ecsContainerMetadataUri)) {
//        return null;
//      }
//      String awsEcsTaskMetadata =
//          new RestTemplate().getForObject(ecsContainerMetadataUri, String.class);
//      HashMap<String, Object> metadata =
//          new ObjectMapper().readValue(awsEcsTaskMetadata, HashMap.class);
//      ArrayList<Object> networks = (ArrayList<Object>) metadata.get("Networks");
//      HashMap<String, Object> network = (HashMap<String, Object>) networks.get(0);
//      ArrayList<Object> ipv4Addresses = (ArrayList<Object>) network.get("IPv4Addresses");
//      return (String) ipv4Addresses.get(0);
//    } catch (Exception e) {
//      return null;
//    }
//  }
//
//  /**
//   * Gets the host ip of local.
//   *
//   * @return the host ip of local
//   */
//  private static String getHostIpOfLocal() {
//    try {
//      InetAddress inetAddress = InetAddress.getLocalHost();
//      return inetAddress.getHostAddress();
//    } catch (UnknownHostException e) {
//      return null;
//    }
//  }
//
//  /**
//   * To proper case.
//   *
//   * @param s the s
//   * @return the string
//   */
//  private static String toProperCase(String s) {
//    return s.substring(0, 1).toUpperCase() +
//        s.substring(1).toLowerCase();
//  }
//
//  /**
//   * Instantiates a new common utils.
//   */
//  private CommonUtils() {}
//}
