/** @format */

import { Breadcrumb, IconButton, Stack, Text } from "@fluentui/react";
import React, { useEffect, useState } from "react";
import SpinnerLoading from "../../../components/multipleComponents/SpinnerLoading";
import PropTypes from "prop-types";
import { itemsFolderTree } from "../../../constants/initialApi/items";
import DirectoryApi from "../../../API/ModuleAPI/DirectoryApi";
import { LIGHT_THEME, MODEL_NAME, ROWS_PER_PAGE } from "../../../constants";
import EmptyContent from "../../../components/layoutComponent/EmptyContent";
import CustomModal from "../../../components/modals";
import CustomText from "../../../components/CustomText";
import CustomButton from "../../../components/CustomButton";
import AddContentSchema from "./AddContentSchema";
import CustomDetailList from "../../../components/layoutComponent/CustomDetailList";
import folderColumnSchema from "../ColumnsSchema/folderColumnSchema";

const warpFolderContent = {
  root: {
    height: "100%",
    display: "flex",
  },
};

const areaContent = {
  root: {
    maxHeight: "calc(100% - 55px)",
    overflowY: "auto",
  },
};

export default function ContentFolder(props) {
  const [isLoadingFolderContent, setIsLoadingFolderContent] = useState(true);
  const [selectFolderId, setSelectFolderId] = useState();
  const [folderTree, setFolderTree] = useState([]);
  const [folders, setFolders] = useState([]);
  const [files, setFiles] = useState([]);
  const [modalName, setModalName] = useState("");
  const [pagination, setPagination] = useState({
    page: 0,
    totalPages: 0,
    pageSize: ROWS_PER_PAGE.TEN,
  });

  const { folder } = props;

  const _getFolderTree = async () => {
    // const folderTreeResult = await DirectoryApi.getFolderTreeByFolderId(
    //   selectFolderId
    // );
    console.log("_getFolderTree success");
  };

  const _getFolders = async () => {
    const data = {
      parentId: selectFolderId,
      ...pagination,
    };
    const folders = await DirectoryApi.getFolderByParentId(data);

    const { items, totalPages } = folders.data;

    setFolders(items);
    setPagination({ ...pagination, totalPages });
    console.log("getFolders success");
  };

  const _getFiles = () => {
    console.log("getFiles success");
  };

  const _loadFolderContent = () => {
    _getFolderTree();
    _getFolders();
    _getFiles();
  };

  useEffect(() => {
    setSelectFolderId(folder?.id);
  }, [folder]);

  useEffect(() => {
    selectFolderId && _loadFolderContent();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [selectFolderId]);

  const _toggleAddContentClick = (name = "") => {
    setModalName(name);
  };

  const _handleChangePageIndex = async page => {
    const data = {
      parentId: selectFolderId,
      ...pagination,
      page,
    };

    const folders = await DirectoryApi.getFolderByParentId(data);
    if (!folders.isAxiosError) {
      const { items, totalPages } = folders.data;
      setFolders(items);
      setPagination({ ...pagination, page, totalPages });
    }
  };

  const _handleChangePageSize = async pageSize => {
    const data = {
      parentId: selectFolderId,
      ...pagination,
      pageSize,
      page: 0,
    };
    const folders = await DirectoryApi.getFolderByParentId(data);
    if (!folders.isAxiosError) {
      const { items, totalPages } = folders.data;
      setFolders(items);
      setPagination({ pageSize, page: 0, totalPages });
    }
  };

  return (
    <Stack styles={warpFolderContent}>
      <Stack horizontal verticalAlign='center' horizontalAlign='space-between'>
        <Breadcrumb
          items={itemsFolderTree}
          maxDisplayedItems={5}
          overflowIndex={1}
        />
        <IconButton
          iconProps={{
            iconName: "more-svg",
            styles: { root: { width: 36, height: 36 } },
          }}
          onClick={() =>
            _toggleAddContentClick(MODEL_NAME.ADD_CONTENT_TO_DOC_FOLDER)
          }
        />
      </Stack>

      {!isLoadingFolderContent ? (
        <SpinnerLoading />
      ) : (
        <Stack styles={areaContent}>
          {folders?.length + files?.length > 0 ? (
            <>
              {folders?.length > 0 ? (
                <CustomDetailList
                  items={folders}
                  columns={folderColumnSchema("folder-svg")}
                  isPagination
                  pagingOptions={{
                    ...pagination,
                    onChangePageIndex: page => _handleChangePageIndex(page),
                    // onChangePageIndex: page =>
                    //   setPagination({ ...pagination, page }),
                    onChangePageSize: pageSize =>
                      _handleChangePageSize(pageSize),
                    //   setPagination({ ...pagination, pageSize, page: 0 }),
                  }}
                />
              ) : null}
              {files?.length > 0 ? (
                <CustomDetailList
                  items={files}
                  isPagination
                  pagingOptions={{
                    ...pagination,
                    onChangePageIndex: page =>
                      setPagination({ ...pagination, page }),
                    onChangePageSize: pageSize =>
                      setPagination({ ...pagination, pageSize, page: 0 }),
                  }}
                />
              ) : null}
            </>
          ) : (
            <EmptyContent
              title='This folder is empty'
              subTitle='Please add content to this folder!'
              imageProps={{
                src: "/img/group-105.png",
                srcSet: "/img/group-105-2x.png 2x, /img/group-105-3x.png 3x",
                alt: "Empty data room content",
                width: 376,
                height: 280,
              }}
              primaryButtonProps={{
                iconProps: {
                  iconName: "plus-svg",
                  styles: {
                    root: {
                      height: 26,
                      fill: LIGHT_THEME.palette.white,
                    },
                  },
                },
                text: "Add Content",
                onClick: () =>
                  _toggleAddContentClick(MODEL_NAME.ADD_CONTENT_TO_DOC_FOLDER),
              }}
            />
          )}
        </Stack>
      )}
      <CustomModal
        isOpen={modalName === MODEL_NAME.ADD_CONTENT_TO_DOC_FOLDER}
        onDismiss={_toggleAddContentClick}
        title='Add content'>
        <AddContentSchema
          directoryId={selectFolderId}
          onDismiss={_toggleAddContentClick}
          fileType={folder?.level}
          refreshDirectory={_loadFolderContent}
        />
      </CustomModal>
    </Stack>
  );
}
ContentFolder.propTypes = {
  folder: PropTypes.object,
};
ContentFolder.defaultProps = {
  folder: {},
};
