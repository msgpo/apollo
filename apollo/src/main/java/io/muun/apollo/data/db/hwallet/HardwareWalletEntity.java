package io.muun.apollo.data.db.hwallet;

import io.muun.apollo.data.db.base.BaseEntity;
import io.muun.apollo.domain.model.HardwareWallet;
import io.muun.common.crypto.hd.PublicKey;
import io.muun.common.model.HardwareWalletBrand;

import android.content.ContentValues;
import android.database.Cursor;
import com.google.auto.value.AutoValue;
import com.squareup.sqldelight.EnumColumnAdapter;

@AutoValue
public abstract class HardwareWalletEntity implements HardwareWalletModel, BaseEntity {

    public static final Factory<HardwareWalletEntity> FACTORY =
            new HardwareWalletModel.Factory<>(
                    AutoValue_HardwareWalletEntity::new,
                    EnumColumnAdapter.create(HardwareWalletBrand.class),
                    ZONED_DATE_TIME_ADAPTER,
                    ZONED_DATE_TIME_ADAPTER
            );

    /**
     * Map from the model to the content values.
     */
    public static ContentValues fromModel(HardwareWallet wallet) {

        return FACTORY.marshal()
                .id(wallet.id == null ? BaseEntity.NULL_ID : wallet.id)
                .hid(wallet.hid)
                .brand(wallet.brand)
                .model(wallet.model)
                .label(wallet.label)
                .base_public_key(wallet.basePublicKey.serializeBase58())
                .base_public_key_path(wallet.basePublicKey.getAbsoluteDerivationPath())
                .created_at(wallet.createdAt)
                .last_paired_at(wallet.lastPairedAt)
                .is_paired(wallet.isPaired)
                .asContentValues();
    }

    /**
     * Map from the database cursor to the model.
     */
    public static HardwareWallet toModel(Cursor cursor) {

        final HardwareWalletEntity entity = FACTORY.selectAllMapper().map(cursor);

        return new HardwareWallet(
                entity.id(),
                entity.hid(),
                entity.brand(),
                entity.model(),
                entity.label(),
                PublicKey.deserializeFromBase58(
                        entity.base_public_key_path(),
                        entity.base_public_key()
                ),
                entity.created_at(),
                entity.last_paired_at(),
                entity.is_paired()
        );
    }
}
