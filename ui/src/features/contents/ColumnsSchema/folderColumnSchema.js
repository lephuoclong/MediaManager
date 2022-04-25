/** @format */

import { Icon, Stack, Text } from "@fluentui/react";
import { icon } from "@fortawesome/fontawesome-svg-core";

const renderNameColumn = (item, iconName) => (
  <Stack horizontal verticalAlign='center'>
    <Icon iconName={iconName} styles={{ root: { width: 36, height: 36 } }} />{" "}
    <Text nowrap styles={{ root: { paddingLeft: 20 } }}>
      {item.name}
    </Text>
  </Stack>
);

const folderColumnSchema = iconName => [
  {
    key: "name",
    name: "name",
    fieldName: "Name",
    minWidth: 250,
    iaSorted: true,
    isSortedDescending: true,
    isResizable: true,
    onRender: item => renderNameColumn(item, iconName),
  },
];

export default folderColumnSchema;
