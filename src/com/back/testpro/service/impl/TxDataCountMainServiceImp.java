package com.back.testpro.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.testpro.dao.TxDataCountMainMapper;
import com.back.testpro.dao.TxDataCountMapper;
import com.back.testpro.model.TxDataCount;
import com.back.testpro.model.TxDataCountMain;
import com.back.testpro.service.TxDataCountMainService;

@Service
public class TxDataCountMainServiceImp implements TxDataCountMainService {

	@Autowired(required = true)
	private TxDataCountMainMapper txDataCountMainMapper;

	@Autowired(required = true)
	private TxDataCountMapper txDataCountMapper;

	public int deleteByPrimaryKey(String tdmId) {
		// TODO Auto-generated method stub
		return txDataCountMainMapper.deleteByPrimaryKey(tdmId);
	}

	public int insert(TxDataCountMain record) {
		// TODO Auto-generated method stub
		if (record.getTdmId() == null || record.getTdmId().equals("")) {
			record.setTdmId(UUID.randomUUID().toString());
		}
		return txDataCountMainMapper.insert(record);
	}

	public int insertSelective(TxDataCountMain record) {
		// TODO Auto-generated method stub
		return txDataCountMainMapper.insertSelective(record);
	}

	public TxDataCountMain selectByPrimaryKey(String tdmId) {
		// TODO Auto-generated method stub
		return txDataCountMainMapper.selectByPrimaryKey(tdmId);
	}

	public int updateByPrimaryKeySelective(TxDataCountMain record) {
		// TODO Auto-generated method stub
		return txDataCountMainMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TxDataCountMain record) {
		// TODO Auto-generated method stub
		return txDataCountMainMapper.updateByPrimaryKey(record);
	}

	public List<TxDataCountMain> queryTemp(TxDataCountMain record) {
		// TODO Auto-generated method stub
		return txDataCountMainMapper.queryTemp(record);
	}

	public List<TxDataCountMain> queryTempThree(TxDataCountMain record) {
		// TODO Auto-generated method stub
		return txDataCountMainMapper.queryTempThree(record);
	}

	public int deleteByPrimaryKeys(String[] ids) {
		// TODO Auto-generated method stub
		return txDataCountMainMapper.deleteByPrimaryKeys(ids);
	}

	public int insertNew(ArrayList<TxDataCountMain> txDataCountMainArrayList,
			ArrayList<TxDataCount> txDataCountArrayList) {
		// TODO Auto-generated method stub
		if (txDataCountMainArrayList != null && txDataCountMainArrayList.size() > 0) {
			for (int i = 0; i < txDataCountMainArrayList.size(); i++) {
				TxDataCountMain txDataCountMain = txDataCountMainArrayList.get(i);
				if (txDataCountMain.getTdmId() == null || txDataCountMain.getTdmId().equals("")) {
					txDataCountMain.setTdmId(UUID.randomUUID().toString());
				}
				txDataCountMainMapper.insert(txDataCountMain);
			}
		}
		if (txDataCountArrayList != null && txDataCountArrayList.size() > 0) {
			for (int i = 0; i < txDataCountArrayList.size(); i++) {
				TxDataCount txDataCount = txDataCountArrayList.get(i);
				if (txDataCount.getTxdcId() == null || txDataCount.getTxdcId().equals("")) {
					txDataCount.setTxdcId(UUID.randomUUID().toString());
				}
				txDataCountMapper.insert(txDataCount);
			}
		}
		return 0;
	}

}
