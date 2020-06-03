package com.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerTest {

//    private static final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger("jsonLogger");
        
        logger.info("Sync completed for chaitanya@chargebeeinc.co.in and api key is live_5WplVhVY5cCPMWKxALPEXmcdwdWZplXQq and phone number is 9994347739",getNumber());
        
        //visa credit card numbers
        logger.info("credit number is 4111111111111111 ");
        logger.info("credit number is 4012888888881881 ");
        logger.info("credit number is 4012-8888-8888-1881 ");
        
        //master card credit card numbers
        logger.info("credit number is 2222405343248877 ");
        logger.info("credit number is 2222 4053 4324 8877 ");//with spaces
        logger.info("credit number is 2222990905257051 ");
        logger.info("credit number is 2222-9909-0525-7051 ");//with hypens
        logger.info("credit number is 2223007648726984 ");
        logger.info("credit number is 2223577120017656 ");
        logger.info("credit number is 5105105105105100 ");
        logger.info("credit number is 5111010030175156 ");
        logger.info("credit number is 5185540810000019 ");
        logger.info("credit number is 5200828282828210 ");
        logger.info("credit number is 5204230080000017 ");
        logger.info("credit number is 5204740009900014 ");

        //discover credit card numbers
        logger.info("credit number is 6011000990139424 ");
        logger.info("credit number is 6011-0009-9013-9424 ");
        logger.info("credit number is 6011111111111117 ");
        
        //American express credit card numbers
        logger.info("credit number is 3714 496353 98431 ");
        logger.info("credit number is 3714-496353-98431 ");
        logger.info("credit number is 371449635398431 ");
        logger.info("credit number is 378282246310005 ");
        
        //Diners credit card numbers
        logger.info("credit number is 3056 9309 0259 04 ");
        logger.info("credit number is 3056-9309-0259-04 ");
        logger.info("credit number is 30569309025904 ");
        logger.info("credit number is 38520000023237 ");
        
        //JCB credit card numbers
        logger.info("credit number is 3530111333300000 ");
        logger.info("credit number is 3530 1113 3330 0000 ");
        logger.info("credit number is 3530-1113-3330-0000 ");
        logger.info("credit number is 3566002020360505 ");
        
        //Negative scenarios for credit card numbers with spaces/hypens
        logger.info("Invalid credit number one : 4012-8888 8888-1881 ");//spaces and hypens
        logger.info("Invalid credit number two : 401288888888---1881 and it gets masked");
        logger.info("Invalid credit number three : 4012-----------1881 and it gets masked ");
        logger.info("Invalid credit number four : 4012           1881 and it gets masked ");
        logger.info("Invalid credit number five : 4053-4324-       and it gets masked ");//specific to visa credit card as last three digits are optional
        
        //positive scenario
        logger.info("valid time stamp 1262494888 in sec and 1262494888000 in milli seconds");

    }

    static int getNumber() {
        return 5;
    }

}