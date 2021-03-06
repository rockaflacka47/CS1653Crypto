/* This list represents the files on the server */
import java.util.*;


	public class FileList implements java.io.Serializable {

	/*Serializable so it can be stored in a file for persistence */
	private static final long serialVersionUID = -8911161283900260136L;
	private ArrayList<ShareFile> list;

	public FileList()
	{
		list = new ArrayList<ShareFile>();
	}

	public synchronized void addFile(String owner, String group, String path, int keyVersion, String name)
	{
		ShareFile newFile = new ShareFile(owner, group, path, keyVersion, name);
		list.add(newFile);
	}

	public synchronized void removeFile(String path)
	{
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getPath().compareTo(path)==0) {
				list.remove(i);
			}
		}
	}

	public synchronized boolean checkFile(String path)
	{
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getPath().compareTo(path)==0) {
				return true;
			}
		}
		return false;
	}

	public synchronized ArrayList<ShareFile> getFiles()
	{
		Collections.sort(list);
		return list;
	}

	public synchronized ShareFile getFile(String path)
	{
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getPath().equals(path)) {
				return list.get(i);
			}
		}
		return null;
	}

	public synchronized String findGroup(String name){
		name = '/' + name;
		for(ShareFile f : list){
			if(f.getName().equals(name)){
				return f.getGroup();
			}
		}
		return null;
	}

	public synchronized int findKeyVersion(String name){
		name = '/' + name;
		for(ShareFile f : list){
			if(f.getName().equals(name)){
				return f.getKeyVersion();
			}
		}
		return -1;
	}
}
