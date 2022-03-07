/** @format */

import { Stack, Text } from "@fluentui/react";
import React from "react";
import PropTypes from "prop-types";

const stylesCustom = styles => ({
  root: {
    ...styles.root,
  },
});

const titleStyles = {
  root: {
    fontWeight: "bold",
    fontFamily: "Georgia",
    fontSize: 30,
  },
};

export default function EmptyList(props) {
  const { children, styles, title } = props;
  return (
    <Stack
      horizontalAlign='center'
      verticalAlign='center'
      styles={stylesCustom(styles)}>
      <Text styles={titleStyles}>{title}</Text>
      <Stack>{children}</Stack>
    </Stack>
  );
}
EmptyList.propTypes = {
  children: PropTypes.node.isRequired,
  styles: PropTypes.objectOf(PropTypes.any),
  title: PropTypes.string.isRequired,
};
EmptyList.defaultProps = {
  styles: undefined,
};
