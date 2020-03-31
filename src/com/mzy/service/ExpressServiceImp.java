package com.mzy.service;

import com.mzy.bean.Express;
import com.mzy.dao.BaseExpressDao;
import com.mzy.dao.ExpressDaoImp;

import java.util.List;

public class ExpressServiceImp implements BaseExpressService {

    private static BaseExpressDao dao = new ExpressDaoImp();

    @Override
    public List<Express> findAll() {
        return dao.findAll();
    }

    @Override
    public boolean deleteById(int id) {
        return dao.deleteByIdOrENumber(null,id);
    }

    @Override
    public String insert(Express e) {
        return dao.insert(e);
    }

    @Override
    public Express findByENumber(String number) {
        return dao.findByENumber(number);
    }

    @Override
    public boolean updateById(int id, Express e) {
        return dao.updateByIdOrENumber(null,id,e);
    }

    /**
     * 通过取件码查询快递
     *
     * @param code
     * @return 快递对象
     */
    @Override
    public Express findByCode(String code) {
        return dao.findByCode(code);
    }

    /**
     * @return
     */
    @Override
    public int size() {
        return dao.size();
    }

    /**
     * 根据快递状态查询所有快递数量
     *
     * @param status    0表示查询未取件快递 1表示查询已取走快递数量
     * @return  结果
     */
    @Override
    public int statusSize(int status) {
        return dao.statusSize(status);
    }

    /**
     * 根据用户手机号查询用户所有快递
     *
     * @param userPhone
     * @return
     */
    @Override
    public List<Express> findByUserPhone(String userPhone) {
        return dao.findByUserPhone(userPhone);
    }

    /**
     * 根据取件码 修改快递状态为已取件, 修改取件时间为当前时间
     *
     * @param code 取件码
     * @return 取件的结果, true表示取件成功, false表示取件失败
     */
    @Override
    public boolean updateStatusByCode(String code) {
        return dao.updateStatusByCode(code);
    }
}
