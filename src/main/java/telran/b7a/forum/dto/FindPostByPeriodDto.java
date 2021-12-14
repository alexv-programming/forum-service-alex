package telran.b7a.forum.dto;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindPostByPeriodDto {
	@JsonFormat
	LocalDate dateFrom;
	@JsonFormat
    LocalDate dateTo;

}
