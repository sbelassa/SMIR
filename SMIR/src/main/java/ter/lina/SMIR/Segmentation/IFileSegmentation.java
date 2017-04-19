package ter.lina.SMIR.Segmentation;

import java.io.IOException;

public interface IFileSegmentation {

	/**
	 * 
	 * @param inputFile the input html file to get content from
	 * @param metaFile where to put the matadata file
	 * @param outputFiles the name for the output files 
	 * @throws IOException
	 */
	void segmentFileToPages(String inputFile, String metaFile, String outputFiles) throws IOException;

}