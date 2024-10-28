package org.example.newsdeneme.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BaseResponse<T> {
	 Boolean success;
	 String message;
	 Integer code;
	 T data;
}