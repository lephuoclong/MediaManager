/** @format */

import AxiosPort from "./../AxiosPort";

class FileApi {
  createNewFile = (fileData, directoryId) => {
    const url = `files/upload/${directoryId}`;
    return AxiosPort.post(url, fileData);
  };

  getFileByParentId = data => {
    const url = `files/${data.parentId}?page=${data.page}&pageSize=${data.pageSize}`;
    return AxiosPort.get(url);
  };

  deleteFileById = id => {
    const url = `files/delete/${id}`;
    return AxiosPort.delete(url);
  };
}

export default new FileApi();