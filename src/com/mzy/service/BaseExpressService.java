package com.mzy.service;

import com.mzy.bean.Express;

import java.util.List;

/**
 * 用于操作快递表格的服务对象
 */
public interface BaseExpressService {
    /**
     * 查询所有快递信息
     * @return  返回查询到的所有的快递对象
     */
    List<Express> findAll();

    /**
     * 根据id删除快递信息
     * @param id 要删除的快递id
     * @return 删除的结果，true返回成功
     */
    boolean deleteById(int id);

    /**
     * 用于操作dao向数据库插入数据
     * @param e 要插入的快递数据
     * @return  插入成功返回取件码，插入失败时返回null
     */
    String insert(Express e);

    /**
     * 通过快递单号 操作dao 从数据库中查询一个快递信息
     * @param number 要查询的快递单号
     * @return  查询的结果，查询失败返回null
     */
    Express findByENumber(String number);

    /**
     * 修改快递信息 通过id
     * @param id 要修改的快递id
     * @param e  新的快递数据对象，包含单号，快递公司，姓名，手机号
     * @return  修改的结果
     */
    boolean updateById(int id,Express e);

    /**
     * 通过取件码查询快递
     * @param code
     * @return  快递对象
     */
    Express findByCode(String code);

    /**
     *
     * @return
     */
    int size();

    /**
     * 根据快递状态查询所有快递数量
     * @param status
     * @return
     */
    int statusSize(int status);

    /**
     * 根据用户手机号查询用户所有快递
     * @param userPhone
     * @return
     */
    List<Express> findByUserPhone(String userPhone);


    /**
     * 根据取件码 修改快递状态为已取件, 修改取件时间为当前时间
     * @param code 取件码
     * @return 取件的结果, true表示取件成功, false表示取件失败
     */
    boolean updateStatusByCode(String code);
}

