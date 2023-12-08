package au.com.geekseat.theta.service;

import au.com.geekseat.theta.helper.Utility;
import au.com.geekseat.theta.model.BaseModel;

public class BaseService {
    public static void toDecorate(BaseModel entity) {
        if (entity == null) {
            return;
        }
        if (entity.getMap() != null) {
            entity.setMapData(Utility.gson.toJson(entity.getMap()));
        }
    }

    public static void fromDecorate(BaseModel entity) {
        if (entity == null) {
            return;
        }
        if (Utility.isNotBlank(entity.getMapData())) {
            entity.setMap(Utility.gson.fromJson(entity.getMapData(), Utility.typeMapOfStringObject));
            entity.setMapData(null);
        }
        if (Utility.isNotBlank(entity.getTransitMapData())) {
            entity.setTransitMap(Utility.gson.fromJson(entity.getTransitMapData(), Utility.typeMapOfStringObject));
            entity.setTransitMapData(null);
        }
    }
}
