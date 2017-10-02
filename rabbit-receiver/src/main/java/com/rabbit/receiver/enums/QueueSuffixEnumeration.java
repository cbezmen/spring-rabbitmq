package com.rabbit.receiver.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QueueSuffixEnumeration {

	ERROR_SUFFIX(".error"), SKIP_SUFFIX(".skip");

	private String suffix;

}
