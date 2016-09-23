package org.iii.see.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.iii.see.domain.ModelData;
import org.iii.see.enumeration.ErrorMessageEnum;
import org.iii.see.exception.DuplicatedDataException;
import org.iii.see.exception.NoDataFoundException;
import org.iii.see.form.ModelDataFormBean;
import org.iii.see.service.ModelIOService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/ModelIO")
public class ModelIOController extends BaseController {

  @Autowired
  private ModelIOService modelIOService;

  @RequestMapping(value = "/doSave", method = RequestMethod.POST)
  public @ResponseBody void doSave(ModelDataFormBean formBean, HttpSession session,
      HttpServletResponse response) throws IOException {

    JSONObject jsonObject = new JSONObject();

    if (formBean.getType() <= 0 || StringUtils.isBlank(formBean.getContent())) {

      jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
      jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_DATA_INCORRECT.getDesc());

      response.setContentType(JKEY_CONTENT_TYPE);
      response.getWriter().write(jsonObject.toString());
      return;
    }

    ModelData modelData = new ModelData();
    BeanUtils.copyProperties(formBean.getModelData(), modelData);

    System.out.println("MMMMMM: " + modelData);
    try {
      ModelData updatedModelData = modelIOService.saveModel(modelData);
      jsonObject.put("uuid", updatedModelData.getUuid());
      jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_SUCCESS);
    } catch (DuplicatedDataException e) {
      jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
      jsonObject.put(JKEY_ERROR_MESSAGE, "模型名稱已經存在");
    } catch (Exception e) {
      e.printStackTrace();
      jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
      jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_UPDATE_FAILED.getDesc());
    }

    response.setContentType(JKEY_CONTENT_TYPE);
    response.getWriter().write(jsonObject.toString());

  }

  @RequestMapping(value = "/doGetList", method = RequestMethod.POST)
  public @ResponseBody void doGetList(ModelDataFormBean formBean, HttpSession session,
      HttpServletResponse response) throws IOException {

    JSONObject jsonObject = new JSONObject();

    if (formBean.getType() <= 0) {

      jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
      jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_DATA_INCORRECT.getDesc());

      response.setContentType(JKEY_CONTENT_TYPE);
      response.getWriter().write(jsonObject.toString());
      return;
    }

    try {

      List<ModelData> modelDataList = modelIOService.queryModelList(formBean.getType());
      StringBuilder uuids = new StringBuilder();
      StringBuilder names = new StringBuilder();
      if (!CollectionUtils.isEmpty(modelDataList)) {
        for (int i = 0; i < modelDataList.size(); i++) {

          if (i > 0) {
            uuids.append(",");
            names.append(",");
          }
          System.out.println(modelDataList.get(i).getUuid());
          System.out.println(modelDataList.get(i).getName());
          uuids.append(modelDataList.get(i).getUuid());
          names.append(modelDataList.get(i).getName());

        }

        jsonObject.put("uuids", uuids.toString());
        jsonObject.put("names", names.toString());

      }

      jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_SUCCESS);

    } catch (Exception e) {
      e.printStackTrace();
      jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
      jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_UNKNOWN_FUNCTION.getDesc());
    }

    response.setContentType(JKEY_CONTENT_TYPE);
    response.getWriter().write(jsonObject.toString());

  }

  @RequestMapping(value = "/doLoad", method = RequestMethod.POST)
  public @ResponseBody void doLoad(ModelDataFormBean formBean, HttpSession session,
      HttpServletResponse response) throws IOException {

    JSONObject jsonObject = new JSONObject();

    if (StringUtils.isBlank(formBean.getUuid())) {

      jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
      jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_DATA_INCORRECT.getDesc());

      response.setContentType(JKEY_CONTENT_TYPE);
      response.getWriter().write(jsonObject.toString());
      return;
    }

    try {

      ModelData modelData = modelIOService.getModelData(formBean.getUuid());
      if (modelData != null) {
        jsonObject.put("uuid", modelData.getUuid());
        jsonObject.put("name", modelData.getName());
        jsonObject.put("content", modelData.getContent());
      }

      jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_SUCCESS);

    } catch (Exception e) {
      e.printStackTrace();
      jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
      jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_UNKNOWN_FUNCTION.getDesc());
    }

    response.setContentType(JKEY_CONTENT_TYPE);
    response.getWriter().write(jsonObject.toString());
  }


  @RequestMapping(value = "/doDelete", method = RequestMethod.POST)
  public @ResponseBody void doDelete(ModelDataFormBean formBean, HttpSession session,
      HttpServletResponse response) throws IOException {

    JSONObject jsonObject = new JSONObject();

    // 檢查欄位值
    if (StringUtils.isBlank(formBean.getUuid())) {

      jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
      jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_DATA_INCORRECT.getDesc());

      response.setContentType(JKEY_CONTENT_TYPE);
      response.getWriter().write(jsonObject.toString());
      return;
    }

    try {

      modelIOService.deleteModel(formBean.getUuid());
      jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_SUCCESS);

    } catch (NoDataFoundException e) {
      jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
      jsonObject.put(JKEY_ERROR_MESSAGE, "模型不存在");
    } catch (Exception e) {
      e.printStackTrace();
      jsonObject.put(JKEY_FUNCTION_STATUS, JVALUE_FUNCTION_FAILED);
      jsonObject.put(JKEY_ERROR_MESSAGE, ErrorMessageEnum.COMMON_DELETE_FAILED.getDesc());
    }

    response.setContentType(JKEY_CONTENT_TYPE);
    response.getWriter().write(jsonObject.toString());

  }

}
