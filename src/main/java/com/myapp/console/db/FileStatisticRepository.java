package com.myapp.console.db;

import com.myapp.console.models.FileModel;
import com.myapp.console.models.FileStatisticModel;
import com.myapp.console.models.LineStatisticModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FileStatisticRepository {
	private static final int BATCH_SIZE = 100;

	private static final String SQL_SAVE_FILE = "INSERT INTO FILE (NAME, PATH) VALUES (?, ?);";

	private static final String SQL_GET_ID_SAVED_FILE = "SELECT ID FROM FILE WHERE PATH = ?";

	private static final String SQL_SAVE_FILE_STATISTIC = "INSERT INTO FILE_AGGREGATION_STATISTIC " +
			"(FILE_ID, LONGEST_WORD, SHORTEST_WORD, LINES_LENGTH, AVERAGE_WORD_LENGTH) " +
			"VALUES (?, ?, ?, ?, ?);";

	private static final String SQL_SAVE_LINE_STATISTIC = "INSERT INTO FILE_LINE_STATISTIC " +
			"(FILE_ID, LINE_NUMBER, LONGEST_WORD, SHORTEST_WORD, LINE_LENGTH, AVERAGE_WORD_LENGTH) " +
			"VALUES (?, ?, ?, ?, ?, ?);";


	public static void saveFile(FileModel fileModel) {
		try (Connection con = DbConnection.getConnection()) {
			con.setAutoCommit(false);
			saveFileOnly(con, fileModel);
			Integer fileId = getSavedFileId(con, fileModel);
			if (fileId == null) {
				throw new SQLException("Houston, we have a problem!");
			}
			saveFileStatistic(con, fileModel, fileId);
			saveLinesStatistic(con, fileModel, fileId);
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void saveLinesStatistic(Connection con, FileModel fileModel, Integer fileId) throws SQLException {
		PreparedStatement saveLinesStatPreparedStatement = con.prepareStatement(SQL_SAVE_LINE_STATISTIC);

		int count = 1;

		for (LineStatisticModel lineStat : fileModel.getLinesStatistic()) {
			saveLinesStatPreparedStatement.setInt(1, fileId);
			saveLinesStatPreparedStatement.setInt(2, count);
			saveLinesStatPreparedStatement.setInt(3, lineStat.getLengthOfLongestWord());
			saveLinesStatPreparedStatement.setInt(4, lineStat.getLengthOfShortestWord());
			saveLinesStatPreparedStatement.setInt(5, lineStat.getLineLength());
			saveLinesStatPreparedStatement.setDouble(6, lineStat.getAverageLengthOfWords());
			saveLinesStatPreparedStatement.execute();
			if((++count - 1) % BATCH_SIZE == 0) {
				saveLinesStatPreparedStatement.executeBatch();
			}
		}
		saveLinesStatPreparedStatement.executeBatch();
	}

	private static void saveFileStatistic(Connection con, FileModel fileModel, Integer fileId) throws SQLException {
		try (PreparedStatement saveFileStatPreparedStatement = con.prepareStatement(SQL_SAVE_FILE_STATISTIC)) {
			FileStatisticModel fileStatistic = fileModel.getFileStatistic();
			saveFileStatPreparedStatement.setInt(1, fileId);
			saveFileStatPreparedStatement.setInt(2, fileStatistic.getLengthOfLongestWord());
			saveFileStatPreparedStatement.setInt(3, fileStatistic.getLengthOfShortestWord());
			saveFileStatPreparedStatement.setInt(4, fileStatistic.getLengthLines());
			saveFileStatPreparedStatement.setDouble(5, fileStatistic.getAverageLengthOfWords());
			saveFileStatPreparedStatement.execute();
		}
	}

	private static Integer getSavedFileId(Connection con, FileModel fileModel) throws SQLException {
		try (PreparedStatement getFileIdPreparedStatement = con.prepareStatement(SQL_GET_ID_SAVED_FILE)) {
			getFileIdPreparedStatement.setString(1, fileModel.getPath());
			ResultSet resultSet = getFileIdPreparedStatement.executeQuery();
			Integer fileId = null;
			while (resultSet.next()) {
				fileId = resultSet.getInt("ID");
			}
			return fileId;
		}
	}

	private static void saveFileOnly(Connection con, FileModel fileModel) throws SQLException {
		try (PreparedStatement saveFilePreparedStatement = con.prepareStatement(SQL_SAVE_FILE)) {
			saveFilePreparedStatement.setString(1, fileModel.getName());
			saveFilePreparedStatement.setString(2, fileModel.getPath());
			saveFilePreparedStatement.execute();
		}
	}
}
