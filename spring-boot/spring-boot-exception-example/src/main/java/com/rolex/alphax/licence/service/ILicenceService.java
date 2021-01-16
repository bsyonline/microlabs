package com.rolex.alphax.licence.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rolex.alphax.licence.dto.*;
import com.rolex.alphax.licence.entity.Licence;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author rolex
 * @since 2020-06-20
 */
public interface ILicenceService extends IService<Licence> {
    LicenceDTO queryDetail(Long licenceId);

    QueryData<SimpleLicenceDTO> getLicences(LicenceParam licenceParam);

    LicenceAddRespData addLicence(LicenceAddRequest request);
}
