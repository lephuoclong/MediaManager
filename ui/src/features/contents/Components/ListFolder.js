/** @format */

import { Stack, TextField } from "@fluentui/react";
import React, { useEffect, useState } from "react";
import DirectoryApi from "../../../API/ModuleAPI/DirectoryApi";
import CustomButton from "../../../components/CustomButton";
import CustomDetailList from "../../../components/layoutComponent/CustomDetailList";
import EmptyContent from "../../../components/layoutComponent/EmptyContent";
import CustomModal from "../../../components/modals";
import { LIGHT_THEME, MODEL_NAME, ROWS_PER_PAGE } from "../../../constants";
import folderColumnSchema from "../ColumnsSchema/folderColumnSchema";
import PropTypes from "prop-types";

const textFieldStyles = {
  field: {
    fontSize: 12,
    lineHeight: 14,
  },
  fieldGroup: {
    height: 40,
    padding: 0,
  },
};

export default function ListFolder(props) {
  const { parentId, selectFolder, fileType } = props;
  const [folders, setFolders] = useState(undefined);
  const [folderName, setFolderName] = useState("");
  const [modalName, setModalName] = useState("");
  const [pagination, setPagination] = useState({
    page: 0,
    totalPages: 1,
    pageSize: ROWS_PER_PAGE.TEN,
  });

  const _handleChangePageIndex = async page => {
    const data = {
      parentId: parentId,
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
      parentId: parentId,
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

  const _getFolders = async () => {
    const data = {
      parentId: parentId,
      ...pagination,
    };
    const folders = await DirectoryApi.getFolderByParentId(data);
    if (!folders.isAxiosError) {
      const { items, totalPages } = folders.data;

      setFolders(items);
      setPagination({ ...pagination, totalPages });
    }
  };

  const _toggleAddContentClick = (name = "") => {
    setModalName(name);
  };

  const _handleFolderNameChange = value => {
    setFolderName(value);
  };

  const _handelDismiss = () => {
    setFolderName();
    _toggleAddContentClick();
  };

  const _submitContent = async () => {
    const data = {
      parentId: parentId,
      name: folderName || "NewFolder",
      level: fileType,
    };

    const createFolder = await DirectoryApi.createFolder(data);

    if (createFolder.isAxiosError) {
      window.alert(createFolder.response.data.message, {
        title: `Error Create Directory!`,
      });
    } else {
      _getFolders();
      _handelDismiss();
    }
  };

  useEffect(() => {
    parentId && _getFolders();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [parentId]);

  return (
    <>
      {folders?.length > 0 ? (
        <CustomDetailList
          items={folders}
          columns={folderColumnSchema("folder-svg", selectFolder, _getFolders)}
          isPagination
          pagingOptions={{
            ...pagination,
            onChangePageIndex: page => _handleChangePageIndex(page),
            onChangePageSize: pageSize => _handleChangePageSize(pageSize),
          }}
        />
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
            text: "Add Folder",
            onClick: () =>
              _toggleAddContentClick(MODEL_NAME.ADD_FOLDER_TO_DOC_FOLDER),
          }}
        />
      )}
      <CustomModal
        isOpen={modalName === MODEL_NAME.ADD_FOLDER_TO_DOC_FOLDER}
        onDismiss={_toggleAddContentClick}
        title='Add folder'>
        <Stack>
          <Stack styles={{ root: { paddingBottom: 20 } }}>
            <TextField
              autoFocus
              value={folderName}
              onChange={(_e, value) => _handleFolderNameChange(value)}
              styles={textFieldStyles}
            />
          </Stack>
          <Stack horizontal horizontalAlign='space-between'>
            <CustomButton text='Cancel' onClick={_handelDismiss} />
            <CustomButton
              primary
              text='Add Folder'
              onClick={_submitContent}
              disabled={folderName === ""}
            />
          </Stack>
        </Stack>
      </CustomModal>
    </>
  );
}
ListFolder.propTypes = {
  parentId: PropTypes.string,
  selectFolder: PropTypes.func,
  fileType: PropTypes.number.isRequired,
};
ListFolder.defaultProps = {
  parentId: undefined,
  selectFolder: undefined,
};
