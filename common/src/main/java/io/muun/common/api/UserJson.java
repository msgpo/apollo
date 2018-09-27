package io.muun.common.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.money.CurrencyUnit;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserJson {

    public Long id;

    public String email;
    public boolean isEmailVerified;

    public PublicProfileJson publicProfile;
    public PhoneNumberJson phoneNumber;

    public CurrencyUnit primaryCurrency;

    public boolean hasPasswordChallengeKey;
    public boolean hasRecoveryCodeChallengeKey;

    public boolean hasP2PEnabled;

    /**
     * Json constructor.
     */
    public UserJson() {
    }

    /**
     * Houston constructor.
     */
    public UserJson(Long id,
                    String email,
                    boolean isEmailVerified,
                    PublicProfileJson publicProfile,
                    PhoneNumberJson phoneNumber,
                    CurrencyUnit primaryCurrency,
                    boolean hasPasswordChallengeKey,
                    boolean hasRecoveryCodeChallengeKey,
                    boolean hasP2PEnabled) {

        this.id = id;
        this.email = email;
        this.isEmailVerified = isEmailVerified;
        this.publicProfile = publicProfile;
        this.phoneNumber = phoneNumber;
        this.primaryCurrency = primaryCurrency;
        this.hasPasswordChallengeKey = hasPasswordChallengeKey;
        this.hasRecoveryCodeChallengeKey = hasRecoveryCodeChallengeKey;
        this.hasP2PEnabled = hasP2PEnabled;
    }
}