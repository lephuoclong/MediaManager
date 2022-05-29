/** @format */

import AxiosPort from "./../AxiosPort";

class DirectoryApi {
  getFolderByParentId = query => {
    const url = `directories/${query.parentId}?page=${query.page}&pageSize=${query.pageSize}`;
    return AxiosPort.get(url);
  };

  getFolderTreeByFolderId(selectFolderId) {
    const url = `directories/folder-tree/${selectFolderId}`;
    return AxiosPort.get(url);
  }

  createFolder = data => {
    const url = `directories`;
    return AxiosPort.post(url, data);
  };

  deleteDirectory = directoryId => {
    const url = `directories/deleted/${directoryId}`;
    return AxiosPort.delete(url);
  };

  addToFavorite = data => {
    const url = `directories/add-favorite`;
    return AxiosPort.post(url, data);
  };
}
export default new DirectoryApi();
