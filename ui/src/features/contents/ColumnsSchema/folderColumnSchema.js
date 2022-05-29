/** @format */

import { Icon, Link, mergeStyleSets, Stack } from "@fluentui/react";
import CustomIconButton from "../../../components/CustomIconButton";

const renderNameColumn = (item, iconName, selectFolder) => (
  <Stack horizontal verticalAlign='center'>
    <Icon iconName={iconName} styles={{ root: { width: 36, height: 36 } }} />{" "}
    <Link
      onClick={() => selectFolder(item.id)}
      styles={{ root: { paddingLeft: 20 } }}>
      {item.name}
    </Link>
  </Stack>
);

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

const _onDeleteFolder = row => {
  //TODO: delete folder
  console.log("delete ", row);
};

const _getFolderMenuProps = row => {
  const result = {
    items: [
      {
        key: "delete",
        text: "Delete",
        onClick: () => _onDeleteFolder(row),
      },
    ],
    directionalHint: 6,
  };
  return result;
};

const _renderActionsBtn = item => {
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
      menuProps={_getFolderMenuProps(item)}
    />
  );
};

const folderColumnSchema = (iconName, selectFolder) => [
  {
    key: "name",
    name: "name",
    fieldName: "Name",
    minWidth: 250,
    iaSorted: true,
    isSortedDescending: true,
    isResizable: true,
    onRender: item => renderNameColumn(item, iconName, selectFolder),
  },
  {
    key: "actions",
    name: "",
    fieldName: "actions",
    minWidth: 35,
    className: classNames.cellAction,
    onRender: item => _renderActionsBtn(item),
  },
];

export default folderColumnSchema;
