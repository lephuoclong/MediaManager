/** @format */

import AxiosPort from "./../AxiosPort";

class DirectoryApi {
  getFolderByParentId = query => {
    const url = `directories/${query.parentId}?page=${query.page}&pageSize=${query.pageSize}`;
    return AxiosPort.get(url);
  };

  getFolderTreeByFolderId(selectFolderId) {
    // const url = `directories/${selectFolderId}`
  }

  createFolder = data => {
    const url = `directories`;
    return AxiosPort.post(url, data);
  };
}
export default new DirectoryApi();
