package com.qianmi.open.api.request;

import com.qianmi.open.api.ApiRuleException;
import com.qianmi.open.api.QianmiRequest;
import com.qianmi.open.api.response.TrainStationsListResponse;
import com.qianmi.open.api.tool.util.QianmiHashMap;

import java.util.Map;

/**
 * API: qianmi.elife.train.stations.list request
 *
 * @author auto
 * @since 1.0
 */
public class TrainStationsListRequest implements QianmiRequest<TrainStationsListResponse> {

    private Map<String, String> headerMap = new QianmiHashMap();
	private QianmiHashMap udfParams; // add user-defined text parameters
	private Long timestamp;

    public Long getTimestamp() {
    	return this.timestamp;
    }
    public void setTimestamp(Long timestamp) {
    	this.timestamp = timestamp;
    }

	public String getApiMethodName() {
		return "qianmi.elife.train.stations.list";
	}

	public Map<String, String> getTextParams() {
		QianmiHashMap txtParams = new QianmiHashMap();
		if(udfParams != null) {
			txtParams.putAll(this.udfParams);
		}
		return txtParams;
	}

	public void putOtherTextParam(String key, String value) {
		if(this.udfParams == null) {
			this.udfParams = new QianmiHashMap();
		}
		this.udfParams.put(key, value);
	}

	public Class<TrainStationsListResponse> getResponseClass() {
		return TrainStationsListResponse.class;
	}

	public void check() throws ApiRuleException {
    }

	public Map<String, String> getHeaderMap() {
        return headerMap;
    }
}