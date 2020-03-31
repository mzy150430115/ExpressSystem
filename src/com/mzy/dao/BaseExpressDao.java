package com.mzy.dao;

import java.util.List;

import com.mzy.bean.Express;

public interface BaseExpressDao {

    /**
     * 查询所有快递
     * @return	包含所有快递对象的集合
     */
    List<Express> findAll();

    /**
     * 通过取件码查询快递
     * @return	快递对象
     */
    Express findByCode(String code);

    /**
     * 查询所有快递数量
     * @return
     */
    int size();
    /**
     * 根据快递状态查询所有快递数量
     * @return
     */
    int statusSize(int status);
    /**
     * 根据单号查询快递
     * @param eNumber 要查询的快递单号
     * @return 查询成功, 返回快递对象 <br> 查询失败返回null
     */
    Express findByENumber(String eNumber);

    /**
     * 通过用户的手机号, 查询用户所有快递
     * @param userPhone 用户手机号码
     * @return 包含用户所有快递的集合
     */
    List<Express> findByUserPhone(String userPhone);

    /**
     * 快件的增加
     * @param e	要增加的快件对象 , 包含了一个快递的所有信息
     * @return 插入成功时, 返回取件码, 插入失败时返回null
     */
    String insert(Express e);

    /**
     * 删除快递 , 通过id或单号
     * @param eNumber 快递单号 ,如果快递单号传入null, 则表示根据id删除, 如果单号不为null,则根据单号删除
     * @param id 快递的id
     * @return 删除的结果, true表示删除成功, false表示删除失败
     */
    boolean deleteByIdOrENumber(String eNumber, int id);
    /**
     * 修改快递 , 通过id或单号
     * @param eNumber 快递单号 ,如果快递单号传入null, 则表示根据id修改, 如果单号不为null,则根据单号修改
     * @param id 快递的id
     * @param newExpress 新的快递信息.
     * @return 修改的结果, true表示修改成功, false表示修改失败
     */
    boolean updateByIdOrENumber(String eNumber, int id, Express newExpress);

    /**
     * 根据取件码 修改快递状态为已取件, 修改取件时间为当前时间
     * @param code 取件码
     * @return 取件的结果, true表示取件成功, false表示取件失败
     */

    boolean updateStatusByCode(String code);
}
