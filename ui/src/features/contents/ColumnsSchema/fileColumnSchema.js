/** @format */

import { Icon, Stack, Link, mergeStyleSets } from "@fluentui/react";
import FileApi from "../../../API/ModuleAPI/FileApi";
import FileBiz from "../../../biz/FileBiz";
import CustomIconButton from "../../../components/CustomIconButton";
import { PAGE_PATHS, TYPE_FILE } from "../../../constants";
import { success } from "../../../components/ToastMessage";

const classNames = mergeStyleSets({
  listWrapper: {
    position: "relative",
    marginBottom: 30,
  },
  cellAction: {
    padding: "0 !important",
    display: "flex !important",
    justifyContent: "center",
    alignItems: "center",
  },
  width100: {
    div: {
      "div.ms-GroupedList": {
        width: "100%",
      },
    },
  },
});

const _renderNameColumn = item => (
  <Stack horizontal verticalAlign='center'>
    <Icon
      iconName={TYPE_FILE.filter(i => i.type === item.type)[0].iconName}
      styles={{ root: { width: 36, height: 36 } }}
    />
    <Link
      href={`/${PAGE_PATHS.VIEW_FILE}/${item.id}`}
      target='_blank'
      styles={{ root: { paddingLeft: 20 } }}>
      {item.name}
    </Link>
  </Stack>
);

const _renderActionsBtn = (item, refreshFile) => {
  return (
    <CustomIconButton
      className='action-btn-overlay'
      styles={{
        root: { paddingRight: 28 },
        menuIcon: { width: 28 },
        rootHovered: {
          svg: { fill: "#363636" },
        },
      }}
      menuIconProps={{ iconName: "more-svg" }}
      title='Actions'
      ariaLabel='Actions'
      menuProps={_getFileMenuProps(item, refreshFile)}
    />
  );
};

const _onDeleteFile = async (row, refreshFile) => {
  const { id } = row;

  const deleteFileResponse = await FileApi.deleteFileById(id);

  if (deleteFileResponse.isAxiosError) {
    window.alert(deleteFileResponse.response.data.message, {
      title: "Delete file failed",
    });
  } else {
    success("Delete file successfully");
    refreshFile();
  }
  console.log("delete ", deleteFileResponse);
};

const _getFileMenuProps = (row, refreshFile) => {
  const result = {
    items: [
      {
        key: "delete",
        text: "Delete",
        onClick: () => _onDeleteFile(row, refreshFile),
      },
    ],
    directionalHint: 6,
  };
  return result;
};

const fileColumnSchema = refreshFile => [
  {
    key: "displayName",
    name: "Name",
    fieldName: "displayName",
    minWidth: 250,
    iaSorted: true,
    isSortedDescending: true,
    isResizable: true,
    onRender: item => _renderNameColumn(item),
  },
  {
    key: "size",
    name: "Size",
    fieldName: "size",
    minWidth: 80,
    maxWidth: 90,
    isRowHeader: true,
    isResizable: true,
    isSortable: true,
    isCollapsible: true,
    data: "string",
    onRender: item => FileBiz.formatSize(item.size),
  },
  {
    key: "actions",
    name: "",
    fieldName: "actions",
    minWidth: 35,
    className: classNames.cellAction,
    onRender: item => _renderActionsBtn(item, refreshFile),
  },
];

export default fileColumnSchema;
