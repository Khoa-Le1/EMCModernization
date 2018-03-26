package ca.ehealthsask.emc.demo.utils;

import ca.ehealthsask.emc.demo.models.entity.MessageInquiryResults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class EMCDateUtils {
    public static String DATEPICKER_FORMAT = "d MMMM, yyyy";
    public static String TIMEPICKER_FORMAT = "hh:mma";

    /**
     * Takes two strings representing date and time and converts them to a LocalDateTime object
     *
     * @param dateInput user entered string for date
     * @param timeInput user entered string for time
     * @return LocalDateTime if a date exists, null if not
     */
    public static LocalDateTime parseFromUserInput(String dateInput, String timeInput) {
        //proceed if date is present
        if (!StringUtils.isBlank(dateInput)) {
            LocalDate localDate = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern(DATEPICKER_FORMAT));

            LocalTime localTime;
            LocalDateTime dateTime;
            //if no time input is available, default is start of day
            if (!StringUtils.isBlank(timeInput)) {
                localTime = LocalTime.parse(timeInput, DateTimeFormatter.ofPattern(TIMEPICKER_FORMAT));
                dateTime = localDate.atTime(localTime);
            } else {
                dateTime = localDate.atStartOfDay();
            }
            log.info("Parsed LocalDateTimeObject: " + dateTime);
            return dateTime;
        } else {
            return null;
        }
    }
}
