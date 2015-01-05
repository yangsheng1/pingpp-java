package com.pingplusplus.model;

import com.google.gson.*;
import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.net.APIResource;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Charge extends APIResource implements MetadataStore<Charge> {
    String id;
    String object;
    Long created;
    Boolean livemode;
    Boolean paid;
    Boolean refunded;
    Object app;
    String channel;
    String orderNo;
    String clientIp;
    Integer amount;
    Integer amountSettle;
    String currency;
    String subject;
    String body;
    Long timePaid;
    Long timeExpire;
    Long timeSettle;
    String transactionNo;
    ChargeRefundCollection refunds;
    Integer amountRefunded;
    String failureCode;
    String failureMsg;
    Map<String, String> metadata;
    Map<String, Object> credential;
    Map<String, String> extra;
    String description;

    public static final Gson PRETTY_PRINT_GSON = new GsonBuilder().
            setPrettyPrinting().
            serializeNulls().
            disableHtmlEscaping().
            setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).
            setLongSerializationPolicy(LongSerializationPolicy.STRING).
            registerTypeAdapter(Double.class, new JsonSerializer<Double>() {
                @Override
                public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
                    if (src == src.longValue())
                        return new JsonPrimitive(src.longValue());
                    return new JsonPrimitive(src);
                }
            }).
            create();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Boolean getLivemode() {
        return livemode;
    }

    public void setLivemode(Boolean livemode) {
        this.livemode = livemode;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Boolean getRefunded() {
        return refunded;
    }

    public void setRefunded(Boolean refunded) {
        this.refunded = refunded;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFailureMsg() {
        return failureMsg;
    }

    public void setFailureMsg(String failureMsg) {
        this.failureMsg = failureMsg;
    }

    public String getFailureCode() {
        return failureCode;
    }

    public void setFailureCode(String failureCode) {
        this.failureCode = failureCode;
    }

    public Integer getAmountRefunded() {
        return amountRefunded;
    }

    public void setAmountRefunded(Integer amountRefunded) {
        this.amountRefunded = amountRefunded;
    }

    public Integer getAmountSettle() {
        return amountSettle;
    }

    public void setAmountSettle(Integer amountSettle) {
        this.amountSettle = amountSettle;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public ChargeRefundCollection getRefunds() {
        // API versions 2014-05-19 and earlier render charge refunds as an array
        // instead of an object, meaning there is no sublist URL.
        if (refunds.getURL() == null) {
            refunds.setURL(String.format("/v1/charges/%s/refunds", getId()));
        }
        return refunds;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Object getApp() {
        return app;
    }

    public void setApp(Object app) {
        this.app = app;
    }

    public String getCredential() {
        Map<String, Object> credParams = new HashMap<String, Object>();
        if (!credential.isEmpty()) {
            credParams.put("object", "credential");
            credParams.put(channel, credential.get(channel));
        }
        return PRETTY_PRINT_GSON.toJson(credParams);
    }

    public void setCredential(Map<String, Object> credential) {
        this.credential = credential;
    }

    public Map<String, String> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, String> extra) {
        this.extra = extra;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getTimePaid() {
        return timePaid;
    }

    public void setTimePaid(Long timePaid) {
        this.timePaid = timePaid;
    }

    public Long getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(Long timeExpire) {
        this.timeExpire = timeExpire;
    }

    public Long getTimeSettle() {
        return timeSettle;
    }

    public void setTimeSettle(Long timeSettle) {
        this.timeSettle = timeSettle;
    }

    public static Charge create(Map<String, Object> params)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return create(params, null);
    }

    public static Charge retrieve(String id) throws AuthenticationException,
            InvalidRequestException, APIConnectionException,
            APIException {
        return retrieve(id, null, null);
    }

    public static Charge retrieve(String id, Map<String, Object> params) throws AuthenticationException,
            InvalidRequestException, APIConnectionException,
            APIException {
        return retrieve(id, params, null);
    }

    public Charge update(Map<String, Object> params)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return update(params, null);
    }

    public static ChargeCollection all(Map<String, Object> params)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return all(params, null);
    }

    public static Charge create(Map<String, Object> params, String apiKey)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return request(RequestMethod.POST, classURL(Charge.class), params,
                Charge.class, apiKey);
    }

    public static Charge retrieve(String id, Map<String, Object> params, String apiKey)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return request(RequestMethod.GET, instanceURL(Charge.class, id), params,
                Charge.class, apiKey);
    }

    public Charge update(Map<String, Object> params, String apiKey)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return request(RequestMethod.POST, instanceURL(Charge.class, id), params,
                Charge.class, apiKey);
    }

    public static ChargeCollection all(Map<String, Object> params, String apiKey)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return request(RequestMethod.GET, classURL(Charge.class), params,
                ChargeCollection.class, apiKey);
    }
}
