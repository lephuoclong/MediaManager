/** @format */

import React from "react";
import PropTypes from "prop-types";
import {
  IconButton,
  Modal,
  PrimaryButton,
  Spinner,
  SpinnerSize,
  Stack,
  Text,
} from "@fluentui/react";
import { LIGHT_THEME } from "../../constants";

const headerModalStyle = {
  root: {
    padding: 10,
    borderBottom: `1px solid ${LIGHT_THEME.palette.neutralQuaternaryAlt}`,
  },
};

const disMissButtonStyles = {
  root: {
    color: `${LIGHT_THEME.palette.neutralQuaternaryAlt}`,
  },
};

const titleStyle = {
  root: {
    color: `${LIGHT_THEME.palette.neutralPrimary}`,
    fontSize: 16,
    fontWeight: 500,
  },
};

const bodyModalStyles = {
  root: {
    padding: 20,
  },
};

export default function CustomModal(props) {
  const {
    title,
    isOpen,
    children,
    onDismiss,
    primaryButtonText,
    primaryButtonProps,
    footerLeft,
    isSubmitting,
    onPrimaryButtonClick,
  } = props;
  return (
    <Modal isOpen={isOpen} onDismiss={onDismiss}>
      <Stack
        horizontal
        horizontalAlign='space-between'
        styles={headerModalStyle}>
        <Text styles={titleStyle}>{title}</Text>
        <IconButton
          onClick={onDismiss}
          iconProps={{ iconName: "ChromeClose" }}
          styles={disMissButtonStyles}
        />
      </Stack>
      <Stack styles={bodyModalStyles}> {children}</Stack>
      {(footerLeft ||
        (primaryButtonText && onPrimaryButtonClick) ||
        primaryButtonProps) && (
        <Stack horizontal horizontalAlign='end'>
          <Stack.Item grow={1}>{footerLeft}</Stack.Item>
          <Stack grow={1} horizontal horizontalAlign='end'>
            {isSubmitting ? <Spinner size={SpinnerSize.medium} /> : null}
            <PrimaryButton text='Cancel' onClick={onDismiss} />
            <PrimaryButton
              text={primaryButtonText}
              onClick={onPrimaryButtonClick}
            />
          </Stack>
        </Stack>
      )}
    </Modal>
  );
}
CustomModal.propTypes = {
  title: PropTypes.string,
  isOpen: PropTypes.bool,
  onDismiss: PropTypes.func,
  onPrimaryButtonClick: PropTypes.func,
};
