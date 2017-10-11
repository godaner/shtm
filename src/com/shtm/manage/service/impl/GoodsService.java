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
import com.shtm.mapper.ClazzsMapper;
import com.shtm.mapper.FilesMapper;
import com.shtm.mapper.GoodsClazzsMapper;
import com.shtm.mapper.GoodsImgsMapper;
import com.shtm.mapper.GoodsMapper;
import com.shtm.mapper.UsersMapper;
import com.shtm.po.Clazzs;
import com.shtm.po.Files;
import com.shtm.po.Goods;
import com.shtm.po.GoodsClazzs;
import com.shtm.po.GoodsClazzsExample;
import com.shtm.po.GoodsClazzsExample.Criteria;
import com.shtm.po.GoodsImgs;
import com.shtm.po.GoodsImgsExample;
import com.shtm.po.Users;
import com.shtm.service.impl.BaseService;

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
	private UsersMapper usersMapper;
	
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
	
	@Autowired
	private ClazzsMapper clazzsMapper;
	
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
		
		/**
		 * 判断状态
		 */
		if(oldStatus != GOODS_STAUS.WAIT_TO_PASS &&
				oldStatus != GOODS_STAUS.UNPASSED &&
				oldStatus != GOODS_STAUS.PASS_SUCCESS &&
				oldStatus != GOODS_STAUS.SELLER_CANCEL &&
				oldStatus != GOODS_STAUS.BUYER_CANCEL){
			eject("更新失败,当前状态的商品不能更新");
		}
		
		//禁止更新字段
		receiver.setBrowsenumber(null);
		receiver.setBuyer(null);
		receiver.setBuytime(null);
		receiver.setCreatetime(null);
		receiver.setFinishtime(null);
		receiver.setOwner(null);
		receiver.setStatus(null);
		//設置字段
		receiver.setLastupdatetime(timestamp());
		
		
		
		//更新goods
		goodsMapper.updateByPrimaryKeySelective(receiver);
		
		
		/**
		 * 更新clazzs
		 */
		GoodsClazzsExample example = new GoodsClazzsExample();
		Criteria criteria = example.createCriteria();
		criteria.andGoodsEqualTo(receiver.getId());
		//查詢當前goods和clazzs的关联記錄
		List<GoodsClazzs> goodsClazzs = goodsClazzsMapper.selectByExample(example);
		//删除舊的clazzs
		goodsClazzsMapper.deleteByExample(example);
		//clazzs数量减-1
		for (int i = 0; i < goodsClazzs.size(); i++) {
			GoodsClazzs sc = goodsClazzs.get(i);
			
			Clazzs c = clazzsMapper.selectByPrimaryKey(sc.getClazz());
			
			c.setNum(c.getNum() -1);
			
			clazzsMapper.updateByPrimaryKeySelective(c);
		}
		
		
		//添加clazzs
		for (String clz : receiver.getClazzs()) {
			if(clz == null){
				continue;
			}
			GoodsClazzs gc = new GoodsClazzs();
			gc.setId(uuid());
			gc.setGoods(receiver.getId());
			gc.setClazz(clz);
			//插入goods和clazzs关联记录
			goodsClazzsMapper.insert(gc);
			//clazzs数量+1
			Clazzs c = clazzsMapper.selectByPrimaryKey(clz);
			
			c.setNum(c.getNum() + 1);
			
			clazzsMapper.updateByPrimaryKeySelective(c);
		}
		
	}


	@Override
	public void deleteGood(GoodsReceiver receiver) throws Exception {
		
		Goods dbGood = goodsMapper.selectByPrimaryKey(receiver.getId());

		eject(dbGood == null || dbGood.getStatus() == GOODS_STAUS.ADMIN_DELETE, "该商品已不存在");
		
		Short oldStatus = dbGood.getStatus();
		
		if(oldStatus != GOODS_STAUS.WAIT_TO_PASS &&
				oldStatus != GOODS_STAUS.UNPASSED &&
				oldStatus != GOODS_STAUS.PASS_SUCCESS &&
				oldStatus != GOODS_STAUS.BUY_BUT_NOT_SEND){
			eject("删除失败,当前状态的商品不能删除");
		}
		
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
	public GoodsReplier selectGoodsImgsDatagrid(GoodsImgsReceiver receiver)
			throws Exception {
		
		GoodsReplier replier = new GoodsReplier();
		
		List<GoodsImgsReplier> rows = customGoodsImgsMapper.selectGoodsImgsDatagrid(receiver);
		
		replier.setTotal(customGoodsImgsMapper.selectGoodsImgsNum(receiver));
		
		replier.setRows(rows);
		
		return replier;
	}

	@Override
	public GoodsImgsReplier uploadGoodsImgs(GoodsImgsReceiver receiver) throws Exception {
		
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
		
		receiver.setId(uuid);
		receiver.setImg(uuid);
		receiver.setMain(GOODS_IMGS_IS_MAIN.NO);
		receiver.setOwner(receiver.getGoodsId());
		
		goodsImgsMapper.insert(receiver);
		
		GoodsImgsReplier replier = new GoodsImgsReplier();
		
		BeanUtils.copyProperties(receiver, replier);
		
		replier.setPath(f.getPath());
		
		return replier;
		
	}

	@Override
	public void updateGoodsMainImg(GoodsImgsReceiver receiver) throws Exception {
		//查询goodsimg的所有者(goodid);
		GoodsImgs gi = goodsImgsMapper.selectByPrimaryKey(receiver.getId());
		
		/**
		 * 更新旧的所有图片为非主图
		 */
		//设置条件
		GoodsImgsExample example = new GoodsImgsExample();
		
		com.shtm.po.GoodsImgsExample.Criteria criteria0 = example.createCriteria();
		
		criteria0.andOwnerEqualTo(gi.getOwner());
		
		
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
		
		//更新商品的最后更新时间
		Goods g = new Goods();
		g.setId(dbGI.getOwner());
		g.setLastupdatetime(timestamp());
		goodsMapper.updateByPrimaryKeySelective(g);
		
		//删除磁盘文件
		deleteVersionsFile( path, fileName,versions);
		
		
		
	}

	@Override
	public void updateGoodsStatus(GoodsReceiver receiver) throws Exception {
		// 判斷商品狀態
		Goods dbGood = goodsMapper.selectByPrimaryKey(receiver.getId());
		// 判断是否存在
		eject(dbGood == null || dbGood.getStatus() == GOODS_STAUS.ADMIN_DELETE,
				"该商品已不存在");

		Short oldStatus = dbGood.getStatus();
		Short newStatus = receiver.getStatus();

		/**
		 * 满足下列状态组合可以更新状态
		 */
		Goods g = new Goods();
		g.setId(receiver.getId());
		g.setStatus(newStatus);

		/**
		 * oldStatus:-6
		 * newStatus:0,-7
		 */
		if (oldStatus == GOODS_STAUS.WAIT_TO_PASS) {
			if (newStatus == GOODS_STAUS.PASS_SUCCESS || 
					newStatus == GOODS_STAUS.UNPASSED) {
				goodsMapper.updateByPrimaryKeySelective(g);
				return;
			} else {
				eject("更新状态失败");
			}
		}
		/**
		 * oldStatus:-8
		 * newStatus:-9,-1
		 */
		if (oldStatus == GOODS_STAUS.WAIT_RETURN_MONEY) {
			if (newStatus == GOODS_STAUS.BUYER_RECEIVED_AND_FINISHED
					|| newStatus == GOODS_STAUS.RETURN_MONEY_SUCCESS) {
				goodsMapper.updateByPrimaryKeySelective(g);
			} else {
				eject("更新状态失败");
			}
		}
		/**
		 * oldStatus:-7
		 * newStatus:0
		 */
		if (oldStatus == GOODS_STAUS.UNPASSED) {
			if (newStatus == GOODS_STAUS.PASS_SUCCESS){
				goodsMapper.updateByPrimaryKeySelective(g);
			} else {
				eject("更新状态失败");
			}
		}
		
		/**
		 * oldStatus:0
		 * newStatus:-7
		 */
		if (oldStatus == GOODS_STAUS.PASS_SUCCESS) {
			if (newStatus == GOODS_STAUS.UNPASSED){
				goodsMapper.updateByPrimaryKeySelective(g);
			} else {
				eject("更新状态失败");
			}
		}
		/**
		 * oldStatus:1
		 * newStatus:-7
		 */
		if (oldStatus == GOODS_STAUS.BUY_BUT_NOT_SEND) {
			if (newStatus == GOODS_STAUS.UNPASSED){
				goodsMapper.updateByPrimaryKeySelective(g);
			} else {
				eject("更新状态失败");
			}
		}
		/**
		 * oldStatus:-2
		 * newStatus:-7
		 */
		if (oldStatus == GOODS_STAUS.SELLER_CANCEL) {
			if (newStatus == GOODS_STAUS.UNPASSED){
				goodsMapper.updateByPrimaryKeySelective(g);
			} else {
				eject("更新状态失败");
			}
		}
		/**
		 * oldStatus:-3
		 * newStatus:-7
		 */
		if (oldStatus == GOODS_STAUS.BUYER_CANCEL) {
			if (newStatus == GOODS_STAUS.UNPASSED){
				goodsMapper.updateByPrimaryKeySelective(g);
			} else {
				eject("更新状态失败");
			}
		}
		/**
		 * oldStatus:-8
		 * newStatus:-9,-1
		 */
		if (oldStatus == GOODS_STAUS.WAIT_RETURN_MONEY) {
			if (newStatus == GOODS_STAUS.RETURN_MONEY_SUCCESS || 
					newStatus == GOODS_STAUS.BUYER_RECEIVED_AND_FINISHED	){
				goodsMapper.updateByPrimaryKeySelective(g);
				if(newStatus == GOODS_STAUS.RETURN_MONEY_SUCCESS){//退款成功
					//退款到buyer用户
					Users dbu = usersMapper.selectByPrimaryKey(dbGood.getBuyer());
					
					if(null != dbu){
						Users nu = new Users();
						
						nu.setId(dbu.getId());
						
						nu.setMoney(dbu.getMoney()+dbGood.getSprice());
						
						usersMapper.updateByPrimaryKeySelective(nu);
					}
					
					
				}
			} else {
				eject("更新状态失败");
			}
		}
		

	}

}
