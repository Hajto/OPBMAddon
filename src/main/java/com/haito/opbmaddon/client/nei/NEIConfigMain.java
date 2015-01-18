package com.haito.opbmaddon.client.nei;

import codechicken.nei.api.IConfigureNEI;
import com.haito.opbmaddon.refference.MainRef;

public class NEIConfigMain implements IConfigureNEI {
    @Override
    public void loadConfig() {

    }

    @Override
    public String getName() {
        return MainRef.modName;
    }

    @Override
    public String getVersion() {
        return MainRef.version;
    }
}
