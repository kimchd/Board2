package org.zerock.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO extends AbstractDAO {

	public BoardDAO() throws Exception {
		super();
	}

	public List<BoardVO> showList() throws Exception {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<BoardVO> list = new ArrayList<>();
		
		try{
			
			con = dataSource.getConnection();
			System.out.println(con);

			
			String sql = "select * from tbl_board2 where bno<10 and bno>0";
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();

			
			
			while (rs.next()) {
				
				
				BoardVO vo = new BoardVO();

				vo.setBno(rs.getInt("bno"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setRegdate(rs.getDate("regdate"));
				vo.setUpdatedate(rs.getDate("updatedate"));
				vo.setGno(rs.getInt("gno"));
				vo.setGord(rs.getInt("gord"));
				vo.setParent(rs.getInt("parent"));

				list.add(vo);

			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			if(rs!=null)try{rs.close();}catch(Exception e){}
			if(psmt!=null)try{psmt.close();}catch(Exception e){}
			if(con!=null)try{con.close();}catch(Exception e){}
		}
		
		return list;
	}

}
