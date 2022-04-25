/** @format */

import React from "react";
import PropTypes from "prop-types";
import { DefaultButton, PrimaryButton, FontSizes } from "@fluentui/react";

const largeButtonStyles = {
  root: {
    height: 44,
    padding: 16,
  },
  label: {
    fontSize: FontSizes.size16,
  },
  icon: {
    width: 28,
    fontWeight: "bold",
  },
};
const mediumButtonStyles = {
  root: {
    height: 40,
    padding: 16,
  },
  label: {
    fontSize: FontSizes.size14,
  },
  icon: {
    width: 26,
    fontWeight: "bold",
  },
};

export default function CustomButton(props) {
  const { styles, primary, size } = props;

  let sizeStyles = styles;
  if (size === "large") {
    sizeStyles = largeButtonStyles;
  }
  if (size === "medium") {
    sizeStyles = mediumButtonStyles;
  }

  if (primary) {
    return <PrimaryButton {...props} styles={sizeStyles} />;
  }
  return <DefaultButton {...props} styles={sizeStyles} />;
}
CustomButton.propTypes = {
  styles: PropTypes.oneOfType([PropTypes.object]),
  primary: PropTypes.bool,
  size: PropTypes.string,
};
CustomButton.defaultProps = {
  styles: undefined,
  primary: false,
  size: undefined,
};
