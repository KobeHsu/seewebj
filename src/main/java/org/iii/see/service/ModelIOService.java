package org.iii.see.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.iii.see.dao.ModelDataDao;
import org.iii.see.domain.ModelData;
import org.iii.see.exception.DuplicatedDataException;
import org.iii.see.exception.NoDataFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
@Transactional
public class ModelIOService {

  @Autowired
  private ModelDataDao modelDataDao;

  public ModelData getModelData(String uuid) {
    return modelDataDao.query(new ModelData(), uuid);
  }

  public List<ModelData> queryModelList(short type) {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("type", type);

    @SuppressWarnings("unchecked")
    List<ModelData> resultSet = (List<ModelData>) modelDataDao
        .queryByNamedQuery("ModelIOService.queryModelListByType", params);

    return resultSet;
  }

  public ModelData saveModel(ModelData modelData) throws DuplicatedDataException {

    Map<String, Object> params = new HashMap<String, Object>();
    params.put("type", modelData.getType());
    params.put("name", modelData.getName());

    @SuppressWarnings("unchecked")
    List<ModelData> resultSet =
        modelDataDao.queryByNamedQuery("ModelIOService.queryModelListByTypeAndName", params);

    ModelData updatedModelData;
    if (CollectionUtils.isEmpty(resultSet)) {

      UUID uuid = UUID.randomUUID();
      modelData.setUuid(String.valueOf(uuid));
      modelData.setEnable("Y");
      modelData.setUpdateTime(new Timestamp((new Date()).getTime()));

      updatedModelData = modelDataDao.update(modelData);

    } else {

      ModelData dbModelData = resultSet.get(0);
      dbModelData.setContent(modelData.getContent());
      dbModelData.setUpdateTime(new Timestamp((new Date()).getTime()));

      updatedModelData = modelDataDao.update(dbModelData);

    }

    return updatedModelData;
  }

  public void deleteModel(String uuid) throws NoDataFoundException {

    ModelData oModelData = modelDataDao.query(new ModelData(), uuid);
    if (oModelData == null) {
      throw new NoDataFoundException();
    }

    modelDataDao.delete(new ModelData(), uuid);

  }

}
