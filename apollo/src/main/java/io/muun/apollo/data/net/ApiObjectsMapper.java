package io.muun.apollo.data.net;

import io.muun.apollo.data.serialization.dates.ApolloZonedDateTime;
import io.muun.apollo.domain.model.BitcoinAmount;
import io.muun.apollo.domain.model.HardwareWallet;
import io.muun.apollo.domain.model.OperationWithMetadata;
import io.muun.apollo.domain.model.PublicProfile;
import io.muun.apollo.domain.model.SubmarineSwapRequest;
import io.muun.apollo.domain.model.UserProfile;
import io.muun.common.api.BitcoinAmountJson;
import io.muun.common.api.ChallengeSetupJson;
import io.muun.common.api.ChallengeSignatureJson;
import io.muun.common.api.ChallengeUpdateJson;
import io.muun.common.api.ExternalAddressesRecord;
import io.muun.common.api.FeedbackJson;
import io.muun.common.api.HardwareWalletJson;
import io.muun.common.api.OperationJson;
import io.muun.common.api.PhoneNumberJson;
import io.muun.common.api.PublicKeyJson;
import io.muun.common.api.PublicProfileJson;
import io.muun.common.api.SignupJson;
import io.muun.common.api.SubmarineSwapRequestJson;
import io.muun.common.api.UserProfileJson;
import io.muun.common.crypto.hd.PublicKey;
import io.muun.common.model.PhoneNumber;
import io.muun.common.model.challenge.ChallengeSetup;
import io.muun.common.model.challenge.ChallengeSignature;
import io.muun.common.utils.Encodings;

import java.util.UUID;

import javax.inject.Inject;
import javax.money.CurrencyUnit;
import javax.validation.constraints.NotNull;

public class ApiObjectsMapper {

    @Inject
    ApiObjectsMapper() {
    }

    /**
     * Create an API phone number.
     */
    @NotNull
    public PhoneNumberJson mapPhoneNumber(PhoneNumber phoneNumber) {
        // By default, when we create a PhoneNumberJson from a phone number
        // on the client isVerified=false , since Houston is the one who should
        // be telling apollo if the phone is verified or not.
        return new PhoneNumberJson(phoneNumber.toE164String(), false);
    }

    /**
     * Create an API public profile.
     */
    @NotNull
    private PublicProfileJson mapPublicProfile(@NotNull PublicProfile publicProfile) {

        return new PublicProfileJson(
                publicProfile.getHid(),
                publicProfile.firstName,
                publicProfile.lastName,
                publicProfile.profilePictureUrl
        );
    }

    /**
     * Create an API user profile.
     */
    @NotNull
    public UserProfileJson mapUserProfile(UserProfile userProfile) {
        return new UserProfileJson(
                userProfile.getFirstName(),
                userProfile.getLastName(),
                userProfile.getPictureUrl()
        );
    }

    /**
     * Create an API bitcoin amount.
     */
    @NotNull
    private BitcoinAmountJson mapBitcoinAmount(@NotNull BitcoinAmount bitcoinAmount) {

        return new BitcoinAmountJson(
                bitcoinAmount.inSatoshis,
                bitcoinAmount.inInputCurrency,
                bitcoinAmount.inPrimaryCurrency
        );
    }

    /**
     * Create an API operation.
     */
    @NotNull
    public OperationJson mapOperation(@NotNull OperationWithMetadata operation) {

        final Long outputAmountInSatoshis = operation.getSwap() != null
                ? operation.getSwap().getFundingOutput().getOutputAmountInSatoshis()
                : operation.getAmount().inSatoshis;

        return new OperationJson(
                UUID.randomUUID().toString(),
                operation.getDirection(),
                operation.isExternal(),
                operation.getSenderProfile() != null
                        ? mapPublicProfile(operation.getSenderProfile()) : null,
                operation.getSenderIsExternal(),
                operation.getReceiverProfile() != null
                        ? mapPublicProfile(operation.getReceiverProfile()) : null,
                operation.getReceiverIsExternal(),
                operation.getReceiverAddress(),
                operation.getReceiverAddressDerivationPath(),
                operation.getHardwareWalletHid(),
                mapBitcoinAmount(operation.getAmount()),
                mapBitcoinAmount(operation.getFee()),
                outputAmountInSatoshis,
                operation.getExchangeRateWindowHid(),
                operation.getDescription(),
                operation.getStatus(),
                ApolloZonedDateTime.of(operation.getCreationDate()),
                operation.getSwap() != null ? operation.getSwap().houstonUuid : null,
                operation.getSenderMetadata(),
                operation.getReceiverMetadata()
        );
    }

    /**
     * Create an API Signup.
     */
    public SignupJson mapSignup(CurrencyUnit primaryCurrency,
                                PublicKey basePublicKey,
                                ChallengeSetup passwordChallengeSetup) {

        return new SignupJson(
                primaryCurrency,
                mapPublicKey(basePublicKey),
                mapChallengeSetup(passwordChallengeSetup)
        );
    }

    /**
     * Create an API external addresses record.
     */
    @NotNull
    public ExternalAddressesRecord mapExternalAddressesRecord(int maxUsedIndex) {

        return new ExternalAddressesRecord(maxUsedIndex);
    }

    /**
     * Create an API public key.
     */
    @NotNull
    public PublicKeyJson mapPublicKey(PublicKey publicKey) {

        return new PublicKeyJson(
                publicKey.serializeBase58(),
                publicKey.getAbsoluteDerivationPath()
        );
    }

    /**
     * Create a ChallengeSetup.
     */
    public ChallengeSetupJson mapChallengeSetup(ChallengeSetup setup) {
        return new ChallengeSetupJson(
                setup.type,
                Encodings.bytesToHex(setup.publicKey.toBytes()),
                Encodings.bytesToHex(setup.salt),
                setup.encryptedPrivateKey,
                setup.version
        );
    }

    /**
     * Create a ChallengeSetup.
     */
    public ChallengeSignatureJson mapChallengeSignature(ChallengeSignature challengeSignature) {
        return new ChallengeSignatureJson(
                challengeSignature.type,
                Encodings.bytesToHex(challengeSignature.bytes)
        );
    }

    /**
     * Create a ChallengeUpdate.
     */
    public ChallengeUpdateJson mapChallengeUpdate(String uuid, ChallengeSetup challengeSetup) {
        return new ChallengeUpdateJson(uuid, mapChallengeSetup(challengeSetup));
    }

    /**
     * Create a Feedback.
     */
    public FeedbackJson mapFeedback(String content) {
        return new FeedbackJson(content);
    }

    /**
     * Create a HardwareWallet.
     */
    public HardwareWalletJson mapHardwareWallet(HardwareWallet wallet) {
        return new HardwareWalletJson(
                wallet.getHid(),
                wallet.getBrand(),
                wallet.getModel(),
                wallet.getLabel(),
                mapPublicKey(wallet.getBasePublicKey()),
                ApolloZonedDateTime.of(wallet.getCreatedAt()),
                ApolloZonedDateTime.of(wallet.getLastPairedAt()),
                wallet.isPaired()
        );
    }

    /**
     * Create a Submarine Swap Request.
     */
    public SubmarineSwapRequestJson mapSubmarineSwapRequest(SubmarineSwapRequest request) {
        return new SubmarineSwapRequestJson(request.invoice, request.swapExpirationInBlocks);
    }
}
