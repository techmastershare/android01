package com.example.plugins;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

public class MediaStoreManager {
	private static String TAG = "MediaStoreManager";
	private Context mContext = null;

	private boolean mIsThumbnailDb = false;

	public MediaStoreManager(Context context) {
		mContext = context;
	}

	// Get all images in the device system
	public MediaInfoList getAllImages() {
		Cursor imageCursor = null;
		try {
			String[] columnList = new String[] {MediaStore.Images.Media._ID,
					MediaStore.Images.Media.DISPLAY_NAME,
					MediaStore.Images.Media.DATA,
					MediaStore.Images.Media.SIZE};
			imageCursor = mContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
					columnList, null, null, null);

			int idColumnIndex = imageCursor.getColumnIndex(MediaStore.Images.Media._ID);
			int displayNameColumnIndex = imageCursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME);
			int filePathColumnIndex = imageCursor.getColumnIndex(MediaStore.Images.Media.DATA);
			int fileSizeColumnIndex = imageCursor.getColumnIndex(MediaStore.Images.Media.SIZE);

			int count = imageCursor.getCount();
			String filePathName = "", displayName = "", thumbnailPathName = "";
			long imageId = 0;
			long fileSize = 0;
			if (count == 0)
				return null;

			String thumbnailDirPath = "";
			// Auto generate thumbnails
			if (mIsThumbnailDb == false) {
				// Create thumbnail folder
				thumbnailDirPath = Environment.getExternalStorageDirectory() + File.separator + Common.THUMBNAIL_FOLDER_NAME;
				File thumbnailDirFile = new File(thumbnailDirPath);
				thumbnailDirFile.mkdirs();
			}

			MediaInfoList mediaInfoList = new MediaInfoList();
			for (int i = 0; i < count; i++) {
				imageCursor.moveToPosition(i);
				imageId = imageCursor.getLong(idColumnIndex);
				filePathName = imageCursor.getString(filePathColumnIndex);
				displayName = imageCursor.getString(displayNameColumnIndex);
				fileSize = imageCursor.getLong(fileSizeColumnIndex);
				if (mIsThumbnailDb == true)
					thumbnailPathName = getThumbnailPathName(imageId);
				else { // Auto generate thumbnails
					thumbnailPathName = thumbnailDirPath + String.format("%s%d.jpg", File.separator, imageId);
					// Generate thumbnail
					generateImageThumbnail(filePathName, thumbnailPathName);
				}

				MediaInfo mediaInfo = new MediaInfo(imageId, displayName, filePathName, fileSize, thumbnailPathName);
				mediaInfoList.add(mediaInfo);
			}
			return mediaInfoList;
		} catch (Exception e) {
			Log.i(TAG, e.toString());
		} finally {
			if(imageCursor != null) {
				imageCursor.close();
			}
		}
		return null;
	}

	// Get thumbnail image uri by file id from database
	public String getThumbnailPathName(long fileId) {
		Cursor thumbCursor = null;
		String[] columnList = new String[] {MediaStore.Images.Thumbnails.DATA};
		String[] selectionArgList = new String[] {String.format("%d", fileId)};
		try {
			thumbCursor = mContext.getContentResolver().query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,
					columnList,
					MediaStore.Images.Thumbnails.IMAGE_ID + " = ?" + " AND "
							+ MediaStore.Images.Thumbnails.KIND + " = "
							+ MediaStore.Images.Thumbnails.MINI_KIND, selectionArgList, null);
			if(thumbCursor.moveToFirst()) {
				// The path is stored in the DATA column
				int dataIndex = thumbCursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
				String thumbnailPath = thumbCursor.getString(dataIndex);
				return thumbnailPath;
			}
		} catch (Exception e) {
			Log.i(TAG, e.toString());
		} finally {
			if(thumbCursor != null) {
				thumbCursor.close();
			}
		}

		return "";
	}

	// Get image information from image path name
	public MediaInfo getImageInfo(final String filePathName) {
		Cursor imageCursor = null;
		String[] columnList = new String[] {MediaStore.Images.Media._ID,
				MediaStore.Images.Media.DISPLAY_NAME,
				MediaStore.Images.Media.SIZE};
		String[] selectionArgList = new String[] {filePathName};

		try {
			imageCursor = mContext.getContentResolver().
					query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
							columnList,
							MediaStore.Images.Media.DATA + " = ?", selectionArgList, null);
			int fileIdColumnIndex = imageCursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
			int displayNameColumnIndex = imageCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME);
			int fileSizeColumnIndex = imageCursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE);

			if(imageCursor.moveToFirst()) {
				// The path is stored in the DATA column
				long fileId = imageCursor.getLong(fileIdColumnIndex);
				String displayName = imageCursor.getString(displayNameColumnIndex);
				long fileSize = imageCursor.getLong(fileSizeColumnIndex);
				String thumbnailPathName = "";
				if (mIsThumbnailDb == true)
					thumbnailPathName = getThumbnailPathName(fileId);
				else {
					String thumbnailDirPath = Environment.getExternalStorageDirectory() + File.separator + Common.THUMBNAIL_FOLDER_NAME;
					thumbnailPathName = thumbnailDirPath + String.format("%s%d.jpg", File.separator, fileId);
					// Generate thumbnail
					generateImageThumbnail(filePathName, thumbnailPathName);
				}

				MediaInfo mediaInfo = new MediaInfo(fileId, displayName, filePathName, fileSize, thumbnailPathName);
				return mediaInfo;
			}
		} catch (Exception e) {
			Log.i(TAG, e.toString());
		} finally {
			if(imageCursor != null) {
				imageCursor.close();
			}
		}

		return null;
	}

	@SuppressLint("NewApi")
	protected boolean generateImageThumbnail(String imagePathName, String thumbnailPathName) {
		try {
			if (imagePathName.isEmpty())
				return false;

			if (thumbnailPathName.isEmpty())
				return false;

			// Check the thumbnail if it is existing
			File thumbnailFile = new File(thumbnailPathName);
			if (thumbnailFile.exists())
				return false;

			/*
			// Get thumbnail directory
			String thumbnailDirPath = thumbnailPathName.substring(0, thumbnailPathName.lastIndexOf(File.separator));
			// Create thumbnail directory
			File thumbnailDirFile = new File(thumbnailDirPath);
			thumbnailDirFile.mkdirs();
			 */

			/*Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
					BitmapFactory.decodeFile(imagePathName), Common.THUMBNAIL_WIDTH, Common.THUMBNAIL_HEIGHT);*/
			
			Bitmap thumbImage = decodeFile(imagePathName, Common.THUMBNAIL_WIDTH, Common.THUMBNAIL_HEIGHT);
			if (thumbImage == null)
				return false;

			//thumbnailFile.createNewFile();
			
			FileOutputStream out = new FileOutputStream(thumbnailFile);
			thumbImage.compress(Bitmap.CompressFormat.JPEG, 100, out);
			out.flush();
			out.close();
			out = null;
			thumbImage = null;
			//System.gc();
		} catch (Exception e) {
			Log.i(TAG, e.toString());
			return false;
		}

		return true;
	}

	// Decodes image and scales it to reduce memory consumption
	private Bitmap decodeFile(String filePathName, int width, int height) {
		try {
	        // Decode image size
	        BitmapFactory.Options o = new BitmapFactory.Options();
	        o.inJustDecodeBounds = true;
	        BitmapFactory.decodeStream(new FileInputStream(filePathName), null, o);

	        // Find the correct scale value. It should be the power of 2.
	        int orgWidth = o.outWidth, orgHeight = o.outHeight;
	        int scale = 1;
	        while(true){
	            if(orgWidth/2 < width || orgHeight/2 < height)
	                break;
	            
	            orgWidth /= 2;
	            orgHeight /= 2;
	            scale *= 2;
	        }

	        // Decode with inSampleSize
	        BitmapFactory.Options o2 = new BitmapFactory.Options();
	        o2.inSampleSize = scale;
	        return BitmapFactory.decodeStream(new FileInputStream(filePathName), null, o2);
	    } catch (Exception e) {
	    	Log.i(TAG, e.toString());
	    }
		
	    return null;
	}
}
