package com.previred.GDD.service;

import com.previred.GDD.model.GddRequest;
import com.previred.GDD.model.GddResponse;

public interface GddService {
	public abstract GddResponse getPeriodos(GddRequest request);
}
