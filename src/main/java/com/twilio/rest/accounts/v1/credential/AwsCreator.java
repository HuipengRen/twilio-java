/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.accounts.v1.credential;

import com.twilio.base.Creator;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class AwsCreator extends Creator<Aws> {
    private final String credentials;
    private String friendlyName;
    private String accountSid;

    /**
     * Construct a new AwsCreator.
     * 
     * @param credentials The credentials
     */
    public AwsCreator(final String credentials) {
        this.credentials = credentials;
    }

    /**
     * The friendly_name.
     * 
     * @param friendlyName The friendly_name
     * @return this
     */
    public AwsCreator setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * The account_sid.
     * 
     * @param accountSid The account_sid
     * @return this
     */
    public AwsCreator setAccountSid(final String accountSid) {
        this.accountSid = accountSid;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Created Aws
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Aws create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.ACCOUNTS.toString(),
            "/v1/Credentials/AWS",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Aws creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }

            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }

        return Aws.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (credentials != null) {
            request.addPostParam("Credentials", credentials.toString());
        }

        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }

        if (accountSid != null) {
            request.addPostParam("AccountSid", accountSid);
        }
    }
}