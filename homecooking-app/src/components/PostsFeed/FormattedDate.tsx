import type { FC } from "react";

type FormattedDateProps = {
  date: Date;
};

export const FormattedDate: FC<FormattedDateProps> = ({ date }) => {
  const now = new Date();
  const diff = (now.getTime() - date.getTime()) / 1000 / 60;

  if (diff < 5) {
    return "Právě teď";
  }

  if (diff < 60) {
    return `Před ${Math.floor(diff)} m`;
  }

  if (diff < 24 * 60) {
    return `Před ${Math.floor(diff / 60)} h`;
  }

  return `Před ${Math.floor(diff / 24 / 26)} d`;
};
