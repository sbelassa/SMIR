The Smart Multimodal Information Retreival Project 

Along with this readme file, there's also the report that gives further explanations and more details about
each phase of the architecture of this project, and also the 'Development choices' part in the annex 
in the report that explains how to install and execute the development project and each of its packages.
You can also check out the javadoc provided with the project to help understand our developments...

You can download the project from the git link below:

--> https://github.com/sbelassa/SMIR.git



Here are the different stages of developping this project and what has been completed or not yet on it:

Extraction:
--> what has been done:
	- the interface 'IExtractToHTML' takes a file as an input, detects its type and then extracts the content
	and the metadata of the file into HTML format
	- the package of extraction options, contains all the classes that we've used in order to understand and
	test tika functionnalities, it has other options of setting and getting metadata, detecting documents
	types and languages, pdf/html parsers...
	
--> what's left to do:
	- completing the 'ExtractToJson' class, in order to separate the extraction of metadata into a json file
	that would be the input for the indexing phase after we add the segmented content to it.





Segmentation:
	- the interface 'IFileSegmentation' that takes the output of tika extraction as an HTML file, and
	 segment the extracted document into pages and returns at the end, segments of HTML documents that 
	 represent each page of the document.
	 - the possibility of adding other methods of segmenting files (X-characters...) 




Indexing:
--> what has been already done:
	- the interface 'IIndexDocument', that takes a json document as an input and creates an index for it then 
	returns the desription of the created index.
	
--> what's left to do:
	- completing and automatizing the suggested solution that we've tested our suggested solution for indexing 
	files' contents by adding the field 'File_content' to the extracted metadata json document by Tika,  
	in order to add the content of the document as the different segmented htlml pages of the document.
	- making the input of the indexing methods as a list of json documents, in order to be able to index
	a group of documents under the same index.
	 




Search:
--> what has been done:
	- the 'Isearch' interface takes the node, query and index on which we aim to apply our search on
	it gives back the results as the numbers of hits found with further details about the index and results,
	the interface gives two methods representing two types of queries: 
	- search string in file: using the query string from the DSL query of elasticsearch that is tested in 
	the form of a multisearch query example
	- search value in a given field from a file: searches for the given value in the given field in an index. 

--> what's left to do:
	- Adding more advanced search queries 
	
	
	
	
Other perspectives:

Adding a User Interface to the project that facilitates the work of this project when allowing the users
to choose their documents in order to extract their content and metadata with giving advanced configuration 
(choosing only certain pages from a document to extract or certain documents from a folder), also giving the
monitoring interface to indexing and the search queries bar, also a view that helps displaying the search results. 