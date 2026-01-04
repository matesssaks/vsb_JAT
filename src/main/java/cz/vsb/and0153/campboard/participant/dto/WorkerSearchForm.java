package cz.vsb.and0153.campboard.participant.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WorkerSearchForm {
	private String name;
	private Boolean includeAll;
}
