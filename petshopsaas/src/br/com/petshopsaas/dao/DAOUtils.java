package br.com.petshopsaas.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

final class DAOUtils {
	private DAOUtils() {
	}

	static Date toSqlDate(LocalDate date) {
		return date == null ? null : Date.valueOf(date);
	}

	static LocalDate toLocalDate(Date date) {
		return date == null ? null : date.toLocalDate();
	}

	static Timestamp toTimestamp(LocalDateTime dt) {
		return dt == null ? null : Timestamp.valueOf(dt);
	}

	static LocalDateTime toLocalDateTime(Timestamp ts) {
		return ts == null ? null : ts.toLocalDateTime();
	}
}
