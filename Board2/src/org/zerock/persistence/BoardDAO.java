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
	
	public List<BoardVO> getPage(int pageNum) throws Exception{
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("select rw, bno, title, writer, regdate, updatedate from( ");
		buffer.append("select rownum rw, bno, title, writer, regdate, updatedate from tbl_board2 where gno>0 order by gno desc, gord asc) ");
		buffer.append("where rw <= "+pageNum+"* 10 and rw >=("+ pageNum +" * 10 )-9 ");
		

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<BoardVO> list = new ArrayList<>();
		
		try {
			
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(buffer.toString());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				BoardVO vo = new BoardVO();
				
				vo.setBno(rs.getInt("bno"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setRegdate(rs.getDate("regdate"));
				vo.setUpdatedate(rs.getDate("updatedate"));

				list.add(vo);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(rs!=null)try{rs.close();}catch(Exception e){}
			if(pstmt!=null)try{pstmt.close();}catch(Exception e){}
			if(con!=null)try{con.close();}catch(Exception e){}
		}
		
		
		return list;
	}
	
	
	public int getListCount() throws Exception {

		int total = -1;
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try{
			con = dataSource.getConnection();

			String sql = "select count(*) from tbl_board2";


			psmt = con.prepareStatement(sql);

			rs = psmt.executeQuery();

			while (rs.next()) {
				total = rs.getInt(1);
			}

			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			if(rs!=null)try{rs.close();}catch(Exception e){}
			if(psmt!=null)try{psmt.close();}catch(Exception e){}
			if(con!=null)try{con.close();}catch(Exception e){}
		}
		return total;
		

	}
	public void insertList(BoardVO vo) throws Exception{
		
	   
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		//새글
		String sql = "insert into tbl_board2 (bno , title, content, writer, gno ) values (seq_board2.nextval , ? , ? , ? ,seq_board2.currval)";
		
		
		try{
			
			
			con = dataSource.getConnection();

			psmt = con.prepareStatement(sql);
			
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getContent());
			psmt.setString(3, vo.getWriter());
			
			psmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			if(rs!=null)try{rs.close();}catch(Exception e){}
			if(psmt!=null)try{psmt.close();}catch(Exception e){}
			if(con!=null)try{con.close();}catch(Exception e){}
		}
	}
	
	public List<BoardVO> contentView(int bno)throws Exception{
		
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<BoardVO> list = new ArrayList<>();
		try{
			con = dataSource.getConnection();
			
			System.out.println("-------------"+bno);
			
			String sql = "select bno, title, content, writer, regdate,updatedate,gno,gord,parent from tbl_board2 where bno = ?";


			psmt = con.prepareStatement(sql);
			
			psmt.setInt(1, bno);
			
			rs = psmt.executeQuery();
			
			if(rs.next()){
				BoardVO vo = new BoardVO();
				vo.setBno(bno);
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setRegdate(rs.getDate("regdate"));
				vo.setUpdatedate(rs.getDate("updatedate"));
				vo.setParent(rs.getInt("parent"));
				vo.setGno(rs.getInt("gno"));
				vo.setGord(rs.getInt("gord"));
				list.add(vo);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			if(rs!=null)try{rs.close();}catch(Exception e){}
			if(psmt!=null)try{psmt.close();}catch(Exception e){}
			if(con!=null)try{con.close();}catch(Exception e){}
		}
		return list;
	}

	public void reply(BoardVO vo)throws Exception{
				//답글
				String sqlRe="insert into tbl_board2 (bno , title, content, writer, gno, gord, parent) values (seq_board2.nextval, ?, ?, ?,?,?,?)";
				//업데이트
				String sqlup="update tbl_board2 set gord = gord+1 where gno=? and gord >=?";
				
				Connection con = null;
				PreparedStatement psmt = null;
				ResultSet rs = null;
				
				String a = null;
				
				try{
					con = dataSource.getConnection();

					con.setAutoCommit(false);
					
					
					psmt = con.prepareStatement(sqlup);
							

					psmt.setInt(1, vo.getGno());
					psmt.setInt(2, vo.getGord()+1);
							
					psmt.executeUpdate();

					psmt.close();

					psmt = con.prepareStatement(sqlRe);
					
					
					psmt.setString(1, vo.getTitle());
					psmt.setString(2, vo.getContent());
					psmt.setString(3, vo.getWriter());
					psmt.setInt(4, vo.getGno());
					psmt.setInt(5, vo.getGord()+1);
					psmt.setInt(6, vo.getParent());
					
					
					psmt.executeUpdate();
					
					con.commit();
				}catch (Exception e) {
					e.printStackTrace();
					con.rollback();
				}finally {
					
					if(rs!=null)try{rs.close();}catch(Exception e){}
					if(psmt!=null)try{psmt.close();}catch(Exception e){}
					if(con!=null)try{con.close();}catch(Exception e){}
				}
	}
}
