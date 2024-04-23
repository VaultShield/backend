package com.vaultshield.passwordmanager.services;

import com.vaultshield.passwordmanager.models.request.RecoverRequest;
import com.vaultshield.passwordmanager.models.response.RecoverResponse;

public interface IRecover {
    RecoverResponse newRecover(RecoverRequest request);
}
