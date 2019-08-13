package com.atb.grpc.ninja.srv;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atb.grpc.ninja.domain.BlockData;


@Service
public class BlockSrv{
	
	@Autowired
	private EncriptSrv encriptSrv;

	public BlockData linkBlockToTheChain(BlockData block) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		block.setHash(encriptSrv.generateHash(assemblyBlock(block)));
		return block;
	}

	public String assemblyBlock(BlockData block) {
		return block.getLinkHash() + block.getTimestamp() + (block.getData()).toString();
	}
	
	public String genBlockHash(BlockData block)
	{
		try {
			return encriptSrv.generateHash(assemblyBlock(block));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			return null;
		}
	}
	
	public boolean checkBlock(String arg)
	{
	return arg.equals(encriptSrv.dummyLinkHashDecode());
	}

}
