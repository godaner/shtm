package com.shtm.manage.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shtm.manage.mapper.CustomGoodsImgsMapper;
import com.shtm.manage.mapper.CustomGoodsMapper;
import com.shtm.manage.po.GoodsImgsReceiver;
import com.shtm.manage.po.GoodsImgsReplier;
import com.shtm.manage.po.GoodsReceiver;
import com.shtm.manage.po.GoodsReplier;
import com.shtm.manage.service.GoodsServiceI;
import com.shtm.mapper.FilesMapper;
import com.shtm.mapper.GoodsClazzsMapper;
import com.shtm.mapper.GoodsImgsMapper;
import com.shtm.mapper.GoodsMapper;
import com.shtm.po.Files;
import com.shtm.po.Goods;
import com.shtm.po.GoodsClazzs;
import com.shtm.po.GoodsClazzsExample;
import com.shtm.po.GoodsClazzsExample.Criteria;
import com.shtm.po.GoodsImgs;
import com.shtm.po.GoodsImgsExample;
import com.shtm.service.impl.BaseService;
import com.shtm.util.Static.CONFIG;
import com.shtm.util.Static.GOODS_IMGS_IS_MAIN;
import com.shtm.util.Static.GOODS_STAUS;

/**
 * Title:GoodsService
 * <p>
 * Description:商品业务接口实现
 * <p>
 * @author Kor_Zhang
 * @date 2017年9月23日 下午2:34:33
 * @version 1.0
 */
@Service
public class GoodsService extends BaseService implements GoodsServiceI {

	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private FilesMapper filesMapper;
	
	@Autowired
	private GoodsClazzsMapper goodsClazzsMapper;
	

	@Autowired
	private GoodsImgsMapper goodsImgsMapper;

	@Autowired
	private CustomGoodsImgsMapper customGoodsImgsMapper;
	
	@Autowired
	private CustomGoodsMapper customGoodsMapper;
	
	@Override
	public GoodsReplier selectGoodsDatagrid(GoodsReceiver receiver)
			throws Exception {
		
		GoodsReplier replier = new GoodsReplier();

		// 查询
		replier.setRows(customGoodsMapper.selectGoodsDatagrid(receiver));

		// 设置记录总数
		Integer totalNum = customGoodsMapper.selectGoodsNum(receiver);

		replier.setTotal(totalNum);

		return replier;
		
	}

	@Override
	public void updateGood(GoodsReceiver receiver) throws Exception {
		//判斷商品狀態
		Goods dbGood = goodsMapper.selectByPrimaryKey(receiver.getId());
		//判断是否存在
		eject(dbGood == null || dbGood.getStatus() == GOODS_STAUS.ADMIN_DELETE, "该商品已不存在");
		
		Short oldStatus = dbGood.getStatus();
		Short newStatus = receiver.getStatus();
		
		if(oldStatus != newStatus){//如果更新了状态
			//旧状态只能为"待审核"和"审核通过"
			eject(oldStatus != GOODS_STAUS.WAIT_TO_PASS && oldStatus != GOODS_STAUS.PASS_SUCCESS ,"当前状态不允许更新");
			
		}
		
		
		//禁止更新字段
		receiver.setBrowsenumber(null);
		receiver.setBuyer(null);
		receiver.setBuytime(null);
		receiver.setCreatetime(null);
		receiver.setFinishtime(null);
		receiver.setOwner(null);
		//設置字段
		receiver.setLastupdatetime(timestamp());
		
		
		
		
		//更新goods
		goodsMapper.updateByPrimaryKeySelective(receiver);
		
		
		/**
		 * 更新clazzs
		 */
		//删除clazzs
		GoodsClazzsExample example = new GoodsClazzsExample();
		Criteria criteria = example.createCriteria();
		criteria.andGoodsEqualTo(receiver.getId());
		goodsClazzsMapper.deleteByExample(example);
		
		//添加clazzs
		for (String clz : receiver.getClazzs()) {
			if(clz == null){
				continue;
			}
			GoodsClazzs gc = new GoodsClazzs();
			gc.setId(uuid());
			gc.setGoods(receiver.getId());
			gc.setClazz(clz);
			goodsClazzsMapper.insert(gc);
		}
		
	}


	@Override
	public void deleteGood(GoodsReceiver receiver) throws Exception {
		
		Goods dbGood = goodsMapper.selectByPrimaryKey(receiver.getId());
		
		eject(dbGood == null || dbGood.getStatus() == GOODS_STAUS.ADMIN_DELETE, "该用户已不存在");
		
		Goods g = new Goods();
		
		g.setId(receiver.getId());
		
		g.setStatus(GOODS_STAUS.ADMIN_DELETE);
		
		goodsMapper.updateByPrimaryKeySelective(g);
		
	}

	@Override
	public GoodsReplier selectGoodsByPK(String id) throws Exception {
		
		Goods g = goodsMapper.selectByPrimaryKey(id);
		
		GoodsReplier replier = new GoodsReplier();
		
		
		BeanUtils.copyProperties(g, replier);
		
		return replier;
	}

	@Override
	public GoodsReplier selectGoodsImgsDatagrid(GoodsReceiver receiver)
			throws Exception {
		
		GoodsReplier replier = new GoodsReplier();
		
		List<GoodsImgsReplier> rows = customGoodsImgsMapper.selectGoodsImgsDatagrid(receiver);
		
		replier.setTotal(customGoodsImgsMapper.selectGoodsImgsNum(receiver));
		
		replier.setRows(rows);
		
		return replier;
	}

	@Override
	public void uploadGoodsImgs(GoodsReceiver receiver) throws Exception {
		
		/**
		 * 判断上传图片的数量
		 */
		//获取指定goodsid的图片数量
		Integer num = customGoodsImgsMapper.selectGoodsImgsNum(receiver);
		
		Integer max = Integer.valueOf(getValue(CONFIG.FILED_GOODS_IMGS_MAXNUMBER).toString());
		
		eject(num >= max, "最多能上传"+max+"张图片");
		
		
		String uuid = uuid();
		/**
		 * 保存源文件
		 */
		String path = getValue(CONFIG.FILED_SRC_GOODS_IMGS).toString();
		String fileName = uuid + "." + getFileNameExt(receiver.getFile().getOriginalFilename());
		
		File targetFile = new File(path,fileName);
		
		receiver.getFile().transferTo(targetFile);
		
		//保存多版本文件
		String versions = getValue(CONFIG.FILED_GOODS_IMGS_SIZES).toString();
		
		writeFileWithCompress(targetFile, versions, path, fileName);
		
		/**
		 * 
		 * 保存files
		 */
		Files f = new Files();
		f.setId(uuid);
		f.setName(fileName);
		f.setPath(fileName);
		
		filesMapper.insert(f);
		
		/**
		 * 保存goodsimgs
		 */
		GoodsImgs gi = new GoodsImgs();
		
		gi.setId(uuid);
		gi.setImg(uuid);
		gi.setMain(GOODS_IMGS_IS_MAIN.NO);
		gi.setOwner(receiver.getId());
		
		goodsImgsMapper.insert(gi);
		
	}

	@Override
	public void updateGoodsMainImg(GoodsImgsReceiver receiver) throws Exception {
		
		/**
		 * 更新旧的所有图片为非主图
		 */
		//设置条件
		GoodsImgsExample example = new GoodsImgsExample();
		
		com.shtm.po.GoodsImgsExample.Criteria criteria0 = example.createCriteria();
		
		criteria0.andOwnerEqualTo(receiver.getOwner());
		
		
		//设置更新字段
		GoodsImgs record = new GoodsImgs();
		
		record.setMain(GOODS_IMGS_IS_MAIN.NO);
		
		goodsImgsMapper.updateByExampleSelective(record, example);
		
		/**
		 * 更新指定goodsimgs的图片为最新的主图
		 */

		
		record.setId(receiver.getId());
		
		record.setMain(GOODS_IMGS_IS_MAIN.YES);
		
		goodsImgsMapper.updateByPrimaryKeySelective(record);
		
	}

	@Override
	public void deleteGoodsImg(GoodsImgsReceiver receiver) throws Exception {
		
		GoodsImgs dbGI = goodsImgsMapper.selectByPrimaryKey(receiver.getId());
		
		eject(dbGI.getMain().equals(GOODS_IMGS_IS_MAIN.YES), "不能删除主图");
		
		//删除files表
		Files f = filesMapper.selectByPrimaryKey(dbGI.getImg());
		String path = getValue(CONFIG.FILED_SRC_GOODS_IMGS).toString();
		String fileName = f.getPath();
		String versions = getValue(CONFIG.FILED_GOODS_IMGS_SIZES).toString();
		
		
		filesMapper.deleteByPrimaryKey(f.getId());
		
		//删除goodsims表
		
		goodsImgsMapper.deleteByPrimaryKey(receiver.getId());
		
		//删除磁盘文件
		deleteVersionsFile( path, fileName,versions);
		
		
		
	}

}
