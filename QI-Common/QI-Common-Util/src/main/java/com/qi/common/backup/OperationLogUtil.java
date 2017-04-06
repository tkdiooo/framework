package com.qi.common.backup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Class OperationLogUtil
 *
 * @author 张麒 2016/4/14.
 * @version Description:
 */
public class OperationLogUtil {

    private String producer;
    private String appId;
    private String topic;
    private String consumer;
    private static OperationLogUtil instance = null;
//    private static KafkaProducer kafkaProducer = null;

//    private static final Logger logger = LoggerFactory.getLogger(OperationLogUtil.class);
//
//    private OperationLogUtil(String producer, String appId, String topic, String consumer) {
//        this.producer = producer;
//        this.appId = appId;
//        this.topic = topic;
//        this.consumer = consumer;
//        setInstance(this);
//    }
//
//    private static void setInstance(OperationLogUtil instance) {
//        OperationLogUtil.instance = instance;
//        kafkaProducer = new KafkaProducer(instance.producer);
//    }
//
//    /**
//     * @param opLog
//     * @author xuwh
//     * @date 2015年8月7日 下午5:17:52
//     * @description 记录日志
//     */
//    public static void recordOpLog(UmSysOperationLogs opLog) {
//        try {
//            if (null == instance) {
//                logger.info("record operation log, instance is null !");
//                return;
//            }
//            if (null == kafkaProducer) {
//                kafkaProducer = new KafkaProducer(instance.producer);
//            }
//            LogVO logVO = new LogVO();
//            logVO.setAppId(instance.appId);
//            logVO.setOperateContent(opLog.getOperatecontent());
//            logVO.setOperateDate(opLog.getOperatedate());
//            logVO.setOperateModule(opLog.getOperatemodule());
//            logVO.setOperateType(opLog.getOperatetype());
//            logVO.setOperator(String.valueOf(opLog.getOperator()));
//            logVO.setOperatorType(opLog.getOperatortype());
//            logVO.setOrgId(opLog.getOrgid());
//            kafkaProducer.send(instance.topic, JSONObject.toJSONString(logVO));
//
//        } catch (Exception e) {
//            logger.error("record operation log error," + e);
//        }
//    }
//
//    /**
//     * @param startPage 起始
//     * @param limitPage 查询条数
//     * @return
//     * @author xuwh
//     * @date 2015年8月7日 下午5:18:08
//     * @description 获取日志
//     */
//    public static List<UmSysOperationLogs> reciveOpLog(int startPage, int limitPage, UmSysOperationLogs opLog) {
//        List<UmSysOperationLogs> tmplist = null;
//        try {
//            if (null == instance) {
//                logger.info("recive operation log, instance is null !");
//                return null;
//            }
//            JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
//            factory.setServiceClass(LogQryService.class);
//            factory.setAddress(instance.consumer);
//            LogQryService api = (LogQryService) factory.create();
//            LogQryRequest logQryRequest = new LogQryRequest();
//            logQryRequest.setAppId(instance.appId);
//            logQryRequest.setStartPage(startPage);
//            logQryRequest.setLimitPage(limitPage);
//            logQryRequest.setOperator(String.valueOf(opLog.getOperator()));
//            logQryRequest.setOperatorType(opLog.getOperatortype());
//            LogQryResponse response = api.query(logQryRequest);
//            logger.info("recive operation log count," + response.getCount());
//            List<LogVO> list = response.getLogList();
//            if (null != list && list.size() > 0) {
//                tmplist = new ArrayList<UmSysOperationLogs>();
//                for (LogVO vo : list) {
//                    UmSysOperationLogs tmp = new UmSysOperationLogs();
//                    tmp.setOrgid(vo.getOrgId());
//                    tmp.setOperatecontent(vo.getOperateContent());
//                    tmp.setOperatedate(vo.getOperateDate());
//                    tmp.setOperatemodule(vo.getOperateModule());
//                    tmp.setOperatetype(vo.getOperateType());
//                    tmp.setOperator(Integer.valueOf(vo.getOperator()));
//                    tmp.setOperatortype(vo.getOperatorType());
//                    tmplist.add(tmp);
//                }
//            }
//        } catch (Exception e) {
//            logger.error("recive operation log error," + e);
//        }
//
//        return tmplist;
//    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
