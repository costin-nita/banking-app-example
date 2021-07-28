package atm.service.config.audit;

import org.springframework.data.auditing.DateTimeProvider;

import java.util.Calendar;
import java.util.TimeZone;

public class DateTimeAuditProvider implements DateTimeProvider {

    @Override
    public Calendar getNow() {
        return Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    }
}
