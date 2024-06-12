const Button = ({
  border = "border-transparent",
  bgColor = "bg-orange-400",
  color = "text-black",
  content,
  height = "h-auto",
  onClick,
  width = "w-auto",
}) => {
  return (
    <button
      onClick={onClick}
      className={`px-12 py-2 ${border} ${bgColor} ${color} ${width} ${height}`}
    >
      {content}
    </button>
  );
};

export default Button;
