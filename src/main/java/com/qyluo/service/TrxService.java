package com.qyluo.service;

import com.qyluo.dao.TrxDao;
import com.qyluo.meta.Trx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ganlee-QY on 2017/4/6.
 */
@Service
public class TrxService {
    @Autowired
    private TrxDao trxDao;

    public List<Trx> getTrxs() {
        return trxDao.getTrxs();
    }
}
