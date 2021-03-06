package io.muun.apollo.domain.action.di;

import io.muun.apollo.domain.action.ContactActions;
import io.muun.apollo.domain.action.CurrencyActions;
import io.muun.apollo.domain.action.HardwareWalletActions;
import io.muun.apollo.domain.action.IntegrityActions;
import io.muun.apollo.domain.action.NotificationActions;
import io.muun.apollo.domain.action.OperationActions;
import io.muun.apollo.domain.action.PhoneActions;
import io.muun.apollo.domain.action.SatelliteActions;
import io.muun.apollo.domain.action.SigninActions;
import io.muun.apollo.domain.action.SyncActions;
import io.muun.apollo.domain.action.UpdateFcmTokenAction;
import io.muun.apollo.domain.action.UserActions;
import io.muun.apollo.domain.action.base.AsyncActionStore;
import io.muun.apollo.domain.action.operation.CreateOperationAction;
import io.muun.apollo.domain.action.operation.FetchNextTransactionSizeAction;
import io.muun.apollo.domain.action.operation.ResolveBitcoinUriAction;
import io.muun.apollo.domain.action.operation.ResolveMuunUriAction;
import io.muun.apollo.domain.action.operation.ResolveOperationUriAction;
import io.muun.apollo.domain.action.operation.SubmitIncomingPaymentAction;
import io.muun.apollo.domain.action.operation.SubmitOutgoingPaymentAction;
import io.muun.apollo.domain.action.operation.SubmitPaymentAction;
import io.muun.apollo.domain.action.operation.UpdateOperationAction;
import io.muun.apollo.domain.action.realtime.FetchRealTimeDataAction;
import io.muun.apollo.domain.action.session.CreateSessionAction;
import io.muun.apollo.domain.action.session.LogInAction;
import io.muun.apollo.domain.action.session.SignUpAction;
import io.muun.apollo.domain.action.session.SyncApplicationDataAction;
import io.muun.apollo.domain.action.user.SendEncryptedKeysEmailAction;
import io.muun.apollo.domain.action.user.UpdateProfilePictureAction;

public interface ActionComponent {

    // Action bags:
    PhoneActions phoneActions();

    SigninActions signinActions();

    ContactActions contactActions();

    OperationActions operationActions();

    UserActions userActions();

    CurrencyActions currencyActions();

    SyncActions feeActions();

    NotificationActions notificationActions();

    AsyncActionStore asyncActionStore();

    SatelliteActions satelliteActions();

    HardwareWalletActions hardwareWalletActions();

    IntegrityActions integrityActions();

    // Own-class actions:
    UpdateProfilePictureAction updateProfilePictureAction();

    FetchRealTimeDataAction fetchRealTimeDataAction();

    FetchNextTransactionSizeAction fetchNextTransactionSizeAction();

    ResolveBitcoinUriAction resolveBitcoinUriAction();

    ResolveMuunUriAction resolveMuunUriAction();

    ResolveOperationUriAction resolveOperationUriAction();

    SubmitPaymentAction submitPaymentAction();

    SubmitIncomingPaymentAction submitIncomingPaymentAction();

    SubmitOutgoingPaymentAction submitOutgoingPaymentAction();

    CreateOperationAction createOperationAction();

    UpdateOperationAction updateOperationAction();

    SendEncryptedKeysEmailAction sendEncryptedKeysEmailAction();

    CreateSessionAction createSessionAction();
    
    SignUpAction signUpAction();

    LogInAction logInAction();

    SyncApplicationDataAction syncApplicationDataAction();

    UpdateFcmTokenAction updateFcmTokenAction();
}
