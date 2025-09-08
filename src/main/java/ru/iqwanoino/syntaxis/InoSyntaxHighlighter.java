package ru.iqwanoino.syntaxis;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.EditText;
import android.graphics.Color;
import android.graphics.Typeface;

import java.util.Stack;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.HashMap;
import java.util.Map;

public class InoSyntaxHighlighter {
	
	public static final int LANGUAGE_XML = 0;
	public static final int LANGUAGE_JSON = 1;
	public static final int LANGUAGE_PHP = 2;
	public static final int LANGUAGE_JAVA = 3;
	public static final int LANGUAGE_JAVASCRIPT = 4;
	public static final int LANGUAGE_HTML = 5;
	public static final int LANGUAGE_CSS = 6;
	public static final int LANGUAGE_PYTHON = 7;
	public static final int LANGUAGE_CPP = 8;
	public static final int LANGUAGE_CSHARP = 9;
	public static final int LANGUAGE_SQL = 10;
	public static final int LANGUAGE_KOTLIN = 11;
	public static final int LANGUAGE_SWIFT = 12;
	public static final int LANGUAGE_RUBY = 13;
	public static final int LANGUAGE_GO = 14;
	public static final int LANGUAGE_RUST = 15;
	public static final int LANGUAGE_MARKDOWN = 16;
	public static final int LANGUAGE_YAML = 17;
	public static final int LANGUAGE_SHELL = 18;
	public static final int LANGUAGE_LUA = 19;
	
	public static Map<Integer, Pattern[]> patternsMap = new HashMap<>();
	public static Map<Integer, int[]> colorsMap = new HashMap<>();
	public static Map<Integer, Boolean[]> boldMap = new HashMap<>();
	
	static {
		initializePatterns();
	}
	
	public static void initializePatterns() {
		// XML
		patternsMap.put(LANGUAGE_XML, new Pattern[]{
			Pattern.compile("</?\\s*([A-Za-z_][\\w:.-]*)"),
			Pattern.compile("\\b([A-Za-z_][\\w:.-]*)(?=\\s*=)"),
			Pattern.compile("\"([^\"\\\\]|\\\\.)*\"|'([^'\\\\]|\\\\.)*'"),
			Pattern.compile("<!--([\\s\\S]*?)-->"),
			Pattern.compile("<!\\[CDATA\\[([\\s\\S]*?)\\]\\]>"),
			Pattern.compile("<!DOCTYPE([\\s\\S]*?)>", Pattern.CASE_INSENSITIVE),
			Pattern.compile("<\\?([\\s\\S]*?)\\?>"),
			Pattern.compile("\\b([A-Za-z_][\\w-]*):(?=[A-Za-z_])"),
			Pattern.compile("&(?:[A-Za-z][A-Za-z0-9]+|#\\d+|#x[0-9A-Fa-f]+);"),
			Pattern.compile("\\b\\d+(?:\\.\\d+)?\\b"),
			Pattern.compile("[<>/=?]+"),
			Pattern.compile("[\\[\\]\\(\\)\\{\\},.;]")
		});
		
		colorsMap.put(LANGUAGE_XML, new int[]{
			Color.parseColor("#569CD6"),
			Color.parseColor("#9CDCFE"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#C586C0"),
			Color.parseColor("#C586C0"),
			Color.parseColor("#C586C0"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#D4D4D4")
		});
		
		boldMap.put(LANGUAGE_XML, new Boolean[]{
			false, false, false, false, false, false, false, false, false, false, false, false
		});
		
		// JSON
		patternsMap.put(LANGUAGE_JSON, new Pattern[]{
			Pattern.compile("\"(.*?)\"(?=\\s*:)"),
			Pattern.compile(":\\s*\"(.*?)\""),
			Pattern.compile("\\b\\d+(\\.\\d+)?\\b"),
			Pattern.compile("\\b(true|false|null)\\b"),
			Pattern.compile("[{}\\[\\]:,]")
		});
		
		colorsMap.put(LANGUAGE_JSON, new int[]{
			Color.parseColor("#9CDCFE"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#569CD6"),
			Color.parseColor("#D4D4D4")
		});
		
		boldMap.put(LANGUAGE_JSON, new Boolean[]{
			false, false, false, false, false
		});
		
		// PHP
		patternsMap.put(LANGUAGE_PHP, new Pattern[]{
			Pattern.compile("\\b(abstract|and|array|as|break|callable|case|catch|class|clone|const|continue|"
			+ "declare|default|do|echo|else|elseif|enddeclare|endfor|endforeach|endif|endswitch|endwhile|"
			+ "extends|final|finally|for|foreach|function|global|goto|if|implements|include|include_once|"
			+ "instanceof|insteadof|interface|namespace|new|or|private|protected|public|require|require_once|"
			+ "return|static|switch|throw|trait|try|unset|use|var|while|xor|yield|true|false|null)\\b"),
			Pattern.compile("\\$[a-zA-Z_][a-zA-Z0-9_]*"),
			Pattern.compile("\"([^\"\\\\]|\\\\.)*\""),
			Pattern.compile("'([^'\\\\]|\\\\.)*'"),
			Pattern.compile("//.*?$", Pattern.MULTILINE),
			Pattern.compile("#.*?$", Pattern.MULTILINE),
			Pattern.compile("/\\*([\\s\\S]*?)\\*/"),
			Pattern.compile("\\b\\d+(\\.\\d+)?\\b"),
			Pattern.compile("[+\\-*/=<>!&|%^~?:]+"),
			Pattern.compile("[{}();,.\\[\\]]"),
			Pattern.compile("<\\?php|\\?>")
		});
		
		colorsMap.put(LANGUAGE_PHP, new int[]{
			Color.parseColor("#569CD6"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#C586C0")
		});
		
		boldMap.put(LANGUAGE_PHP, new Boolean[]{
			true, false, false, false, false, false, false, false, false, false, false
		});
		
		// Java
		patternsMap.put(LANGUAGE_JAVA, new Pattern[]{
			Pattern.compile("\\b(abstract|assert|boolean|break|byte|case|catch|char|class|const|continue|"
			+ "default|do|double|else|enum|extends|final|finally|float|for|goto|if|implements|import|"
			+ "instanceof|int|interface|long|native|new|null|package|private|protected|public|return|"
			+ "short|static|strictfp|super|switch|synchronized|this|throw|throws|transient|try|void|volatile|while)\\b"),
			Pattern.compile("\\b([A-Z][a-zA-Z0-9_]*)\\b"),
			Pattern.compile("\"([^\"\\\\]|\\\\.)*\""),
			Pattern.compile("'([^'\\\\]|\\\\.)*'"),
			Pattern.compile("//.*?$", Pattern.MULTILINE),
			Pattern.compile("/\\*([\\s\\S]*?)\\*/"),
			Pattern.compile("\\b\\d+(\\.\\d+)?\\b"),
			Pattern.compile("[+\\-*/=<>!&|%^~?:]+"),
			Pattern.compile("[{}();,.\\[\\]]")
		});
		
		colorsMap.put(LANGUAGE_JAVA, new int[]{
			Color.parseColor("#569CD6"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#D4D4D4")
		});
		
		boldMap.put(LANGUAGE_JAVA, new Boolean[]{
			true, false, false, false, false, false, false, false, false
		});
		
		// JavaScript
		patternsMap.put(LANGUAGE_JAVASCRIPT, new Pattern[]{
			Pattern.compile("\\b(function|var|let|const|if|else|for|while|do|switch|case|break|continue|"
			+ "return|try|catch|finally|throw|new|this|class|extends|super|import|export|default|"
			+ "true|false|null|undefined|typeof|instanceof|void|in|of|delete|yield|async|await|debugger)\\b"),
			Pattern.compile("\\b([A-Z][a-zA-Z0-9_]*)\\b"),
			Pattern.compile("\"([^\"\\\\]|\\\\.)*\""),
			Pattern.compile("'([^'\\\\]|\\\\.)*'"),
			Pattern.compile("`([^`\\\\]|\\\\.)*`"),
			Pattern.compile("//.*?$", Pattern.MULTILINE),
			Pattern.compile("/\\*([\\s\\S]*?)\\*/"),
			Pattern.compile("\\b\\d+(\\.\\d+)?\\b"),
			Pattern.compile("[+\\-*/=<>!&|%^~?:]+"),
			Pattern.compile("[{}();,.\\[\\]]"),
			Pattern.compile("/.*?/[gimuy]*")
		});
		
		colorsMap.put(LANGUAGE_JAVASCRIPT, new int[]{
			Color.parseColor("#569CD6"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#C586C0")
		});
		
		boldMap.put(LANGUAGE_JAVASCRIPT, new Boolean[]{
			true, false, false, false, false, false, false, false, false, false, false
		});
		
		// Python
		patternsMap.put(LANGUAGE_PYTHON, new Pattern[]{
			Pattern.compile("\\b(def|class|if|elif|else|for|while|try|except|finally|with|as|import|from|"
			+ "return|yield|lambda|pass|break|continue|global|nonlocal|assert|del|and|or|not|in|is|None|True|False)\\b"),
			Pattern.compile("\\b([A-Z][a-zA-Z0-9_]*)\\b"),
			Pattern.compile("\"\"\"([\\s\\S]*?)\"\"\"|'''([\\s\\S]*?)'''"),
			Pattern.compile("\"([^\"\\\\]|\\\\.)*\""),
			Pattern.compile("'([^'\\\\]|\\\\.)*'"),
			Pattern.compile("#.*?$", Pattern.MULTILINE),
			Pattern.compile("\\b\\d+(\\.\\d+)?\\b"),
			Pattern.compile("[+\\-*/=<>!&|%^~?:@]+"),
			Pattern.compile("[{}();,.\\[\\]]"),
			Pattern.compile("\\b(self|cls)\\b")
		});
		
		colorsMap.put(LANGUAGE_PYTHON, new int[]{
			Color.parseColor("#569CD6"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#9CDCFE")
		});
		
		boldMap.put(LANGUAGE_PYTHON, new Boolean[]{
			true, false, false, false, false, false, false, false, false, false
		});
		
		//HTML
		patternsMap.put(LANGUAGE_HTML, new Pattern[]{
			Pattern.compile("</?\\b([a-zA-Z][a-zA-Z0-9]*)\\b"),
			Pattern.compile("\\b([a-zA-Z-:]+)(?=\\=)"),
			Pattern.compile("\"[^\"]*\"|'[^']*'"),
			Pattern.compile("<!--([\\s\\S]*?)-->"),
			Pattern.compile("\\b([a-zA-Z-]+)(?=\\s*:)"),
			Pattern.compile(":[^;]+;"),
			Pattern.compile("\\b(#[0-9a-fA-F]{3,6})\\b"),
			Pattern.compile("\\b\\d+(px|em|rem|vh|vw|%)?\\b"),
			Pattern.compile("\\b(display|flex|block|inline|grid|none|relative|absolute)\\b"),
			Pattern.compile("\\b(function|var|let|const|if|else|for|while|return|document|window|new|class|this|import|export)\\b"),
			Pattern.compile("\\b(true|false|null|undefined)\\b"),
			Pattern.compile("//.*?$", Pattern.MULTILINE),
			Pattern.compile("/\\*([\\s\\S]*?)\\*/"),
			Pattern.compile("[+\\-*/=<>!]+"),
			Pattern.compile("[{}();,.]")
		});
		
		colorsMap.put(LANGUAGE_HTML, new int[]{
			Color.parseColor("#569CD6"),
			Color.parseColor("#9CDCFE"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#C586C0"),
			Color.parseColor("#D7BA7D"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#569CD6"),
			Color.parseColor("#569CD6"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#C586C0")
		});
		
		boldMap.put(LANGUAGE_HTML, new Boolean[]{
			false, false, false, false,
			false, false, false, false,
			false, false, false, false,
			false, false, false, false
		});
		
		// CSS
		patternsMap.put(LANGUAGE_CSS, new Pattern[]{
			Pattern.compile("\\b(@import|@media|@keyframes|@font-face|@page|@charset|@namespace|@supports|@document)\\b"),
			Pattern.compile("\\b(\\w+-)*\\w+(?=\\s*:)"),
			Pattern.compile("#[0-9A-Fa-f]{3,6}"),
			Pattern.compile("\\b(\\d+)(px|em|rem|%|pt|cm|mm|in|pc|ex|ch|vw|vh|vmin|vmax|deg|rad|grad|turn|s|ms)\\b"),
			Pattern.compile("\\b(url)\\(.*?\\)"),
			Pattern.compile("\\b(important|initial|inherit|unset|default)\\b"),
			Pattern.compile("\\b(\\w+)\\s*(?=\\{)"),
			Pattern.compile("\"([^\"\\\\]|\\\\.)*\"|'([^'\\\\]|\\\\.)*'"),
			Pattern.compile("//.*?$", Pattern.MULTILINE),
			Pattern.compile("/\\*([\\s\\S]*?)\\*/"),
			Pattern.compile("\\.[A-Za-z_][\\w-]*"),
			Pattern.compile("#[A-Za-z_][\\w-]*"),
			Pattern.compile(":[A-Za-z_][\\w-]*"),
			Pattern.compile("\\[[A-Za-z_][\\w-]*(\\*|\\^|\\$|\\|)?=[^\\]]*\\]")
		});
		
		colorsMap.put(LANGUAGE_CSS, new int[]{
			Color.parseColor("#569CD6"),
			Color.parseColor("#9CDCFE"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#569CD6"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#4EC9B0")
		});
		
		boldMap.put(LANGUAGE_CSS, new Boolean[]{
			true, false, false, false, false, true, false, false, false, false,
			false, false, false, false
		});
		
		// C++
		patternsMap.put(LANGUAGE_CPP, new Pattern[]{
			Pattern.compile("\\b(alignas|alignof|and|and_eq|asm|auto|bitand|bitor|bool|break|"
			+ "case|catch|char|char16_t|char32_t|class|compl|const|constexpr|const_cast|"
			+ "continue|decltype|default|delete|do|double|dynamic_cast|else|enum|explicit|"
			+ "export|extern|false|float|for|friend|goto|if|inline|int|long|mutable|"
			+ "namespace|new|noexcept|not|not_eq|nullptr|operator|or|or_eq|private|"
			+ "protected|public|register|reinterpret_cast|return|short|signed|sizeof|"
			+ "static|static_assert|static_cast|struct|switch|template|this|thread_local|"
			+ "throw|true|try|typedef|typeid|typename|union|unsigned|using|virtual|void|"
			+ "volatile|wchar_t|while|xor|xor_eq)\\b"),
			Pattern.compile("\\b(std|cout|cin|cerr|clog|endl|string|vector|map|set|list|array)\\b"),
			Pattern.compile("\\b([A-Z][a-zA-Z0-9_]*)\\b"),
			Pattern.compile("\"([^\"\\\\]|\\\\.)*\""),
			Pattern.compile("'([^'\\\\]|\\\\.)*'"),
			Pattern.compile("//.*?$", Pattern.MULTILINE),
			Pattern.compile("/\\*([\\s\\S]*?)\\*/"),
			Pattern.compile("\\b\\d+(\\.\\d+)?([eE][+-]?\\d+)?[fF]?\\b"),
			Pattern.compile("0[xX][0-9A-Fa-f]+"),
			Pattern.compile("[+\\-*/=<>!&|%^~?:]+"),
			Pattern.compile("[{}();,.\\[\\]]"),
			Pattern.compile("#\\s*(include|define|ifdef|ifndef|endif|if|else|elif|pragma|error)\\b")
		});
		
		colorsMap.put(LANGUAGE_CPP, new int[]{
			Color.parseColor("#569CD6"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#C586C0")
		});
		
		boldMap.put(LANGUAGE_CPP, new Boolean[]{
			true, false, false, false, false, false, false, false, false, false,
			false, false
		});
		
		// C#
		patternsMap.put(LANGUAGE_CSHARP, new Pattern[]{
			Pattern.compile("\\b(abstract|as|base|bool|break|byte|case|catch|char|checked|class|const|"
			+ "continue|decimal|default|delegate|do|double|else|enum|event|explicit|extern|false|"
			+ "finally|fixed|float|for|foreach|goto|if|implicit|in|int|interface|internal|is|lock|"
			+ "long|namespace|new|null|object|operator|out|override|params|private|protected|public|"
			+ "readonly|ref|return|sbyte|sealed|short|sizeof|stackalloc|static|string|struct|switch|"
			+ "this|throw|true|try|typeof|uint|ulong|unchecked|unsafe|ushort|using|virtual|void|"
			+ "volatile|while|get|set|value|var|yield|async|await|nameof|when|partial|where|select|"
			+ "from|group|into|orderby|join|let|equals|by|ascending|descending|dynamic|global)\\b"),
			Pattern.compile("\\b([A-Z][a-zA-Z0-9_]*)\\b"),
			Pattern.compile("\"([^\"\\\\]|\\\\.)*\""),
			Pattern.compile("'([^'\\\\]|\\\\.)*'"),
			Pattern.compile("@\"([^\"]|\"\")*\""),
			Pattern.compile("//.*?$", Pattern.MULTILINE),
			Pattern.compile("/\\*([\\s\\S]*?)\\*/"),
			Pattern.compile("\\b\\d+(\\.\\d+)?([eE][+-]?\\d+)?[fFdDmM]?\\b"),
			Pattern.compile("0[xX][0-9A-Fa-f]+"),
			Pattern.compile("[+\\-*/=<>!&|%^~?:]+"),
			Pattern.compile("[{}();,.\\[\\]]"),
			Pattern.compile("#\\s*(if|else|elif|endif|define|undef|warning|error|line|region|endregion)\\b")
		});
		
		colorsMap.put(LANGUAGE_CSHARP, new int[]{
			Color.parseColor("#569CD6"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#C586C0")
		});
		
		boldMap.put(LANGUAGE_CSHARP, new Boolean[]{
			true, false, false, false, false, false, false, false, false, false,
			false, false
		});
		
		// SQL
		patternsMap.put(LANGUAGE_SQL, new Pattern[]{
			Pattern.compile("\\b(SELECT|FROM|WHERE|INSERT|UPDATE|DELETE|JOIN|INNER|LEFT|RIGHT|OUTER|"
			+ "FULL|UNION|ALL|CREATE|ALTER|DROP|TABLE|VIEW|INDEX|DATABASE|TRIGGER|PROCEDURE|FUNCTION|"
			+ "VALUES|INTO|SET|GROUP BY|ORDER BY|HAVING|AS|ON|AND|OR|NOT|IN|BETWEEN|LIKE|IS|NULL|"
			+ "EXISTS|CASE|WHEN|THEN|ELSE|END|DISTINCT|TOP|LIMIT|OFFSET|FETCH|NEXT|ONLY|WITH|"
			+ "ROLLUP|CUBE|GRANT|REVOKE|COMMIT|ROLLBACK|SAVEPOINT|PRIMARY KEY|FOREIGN KEY|REFERENCES|"
			+ "UNIQUE|CHECK|DEFAULT|CONSTRAINT|ASC|DESC|CAST|CONVERT|EXTRACT|DATE|TIME|TIMESTAMP|"
			+ "INTERVAL|BOOLEAN|INTEGER|INT|BIGINT|SMALLINT|TINYINT|DECIMAL|NUMERIC|FLOAT|REAL|"
			+ "DOUBLE|CHAR|VARCHAR|TEXT|BLOB|CLOB|BYTEA|UUID|JSON|XML|ARRAY|TRUE|FALSE|NULLIF|"
			+ "COALESCE|IFNULL|NVL|COUNT|SUM|AVG|MIN|MAX|FIRST|LAST|RANK|DENSE_RANK|ROW_NUMBER|"
			+ "OVER|PARTITION BY|LEAD|LAG|FIRST_VALUE|LAST_VALUE|NTILE|PERCENT_RANK|CUME_DIST|"
			+ "EXCEPT|INTERSECT|PIVOT|UNPIVOT|MATERIALIZED|TEMP|TEMPORARY|GLOBAL|LOCAL|"
			+ "SESSION|AUTHORIZATION|CASCADE|RESTRICT|IF|BEGIN|END|DECLARE|LOOP|WHILE|FOR|FOREACH|"
			+ "EXIT|CONTINUE|RETURN|CALL|EXECUTE|USING|PREPARE|DEALLOCATE|TRUNCATE|ANALYZE|EXPLAIN|"
			+ "VACUUM|REINDEX|CLUSTER|BACKUP|RESTORE|LOAD|COPY|LOCK|SHOW|DESCRIBE|DESC|HELP|USE|"
			+ "CONNECT|DISCONNECT|CHANGE|SET|RESET|SHOW|KILL|PURGE|OPTIMIZE|CHECKPOINT|"
			+ "WAITFOR|DELAY|TIME|TIMEOUT|RAISE|THROW|TRY|CATCH|FINALLY|MERGE|OUTPUT|OUT|INOUT|"
			+ "READS|MODIFIES|DETERMINISTIC|LANGUAGE|SQL|PSM|PLSQL|TRAN|TRANSACTION|ISOLATION|LEVEL|"
			+ "READ|WRITE|COMMITTED|UNCOMMITTED|REPEATABLE|SERIALIZABLE|SNAPSHOT|WITH|HINT|FORCE|"
			+ "IGNORE|STATISTICS|NORECOMPUTE|ROWGUIDCOL|IDENTITY|SEED|INCREMENT|CACHE|NOCOUNT|"
			+ "NOEXEC|PARSEONLY|PROFILE|ROWCOUNT|TEXTSIZE|ARITHABORT|ARITHIGNORE|CONCAT_NULL_YIELDS_NULL|"
			+ "QUOTED_IDENTIFIER|ANSI_NULLS|ANSI_PADDING|ANSI_WARNINGS|NUMERIC_ROUNDABORT|XACT_ABORT)\\b", Pattern.CASE_INSENSITIVE),
			Pattern.compile("'([^'\\\\]|\\\\.)*'"),
			Pattern.compile("\"([^\"\\\\]|\\\\.)*\""),
			Pattern.compile("--.*?$", Pattern.MULTILINE),
			Pattern.compile("/\\*([\\s\\S]*?)\\*/"),
			Pattern.compile("\\b\\d+(\\.\\d+)?\\b"),
			Pattern.compile("[+\\-*/=<>!&|%^~?:]+"),
			Pattern.compile("[{}();,.\\[\\]]"),
			Pattern.compile("@[A-Za-z_][A-Za-z0-9_]*"),
			Pattern.compile(":[A-Za-z_][A-Za-z0-9_]*"),
			Pattern.compile("\\b\\d{4}-\\d{2}-\\d{2}(\\s+\\d{2}:\\d{2}:\\d{2}(\\.\\d+)?)?\\b")
		});
		
		colorsMap.put(LANGUAGE_SQL, new int[]{
			Color.parseColor("#569CD6"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#B5CEA8")
		});
		
		boldMap.put(LANGUAGE_SQL, new Boolean[]{
			true, false, false, false, false, false, false, false, false, false,
			false
		});
		
		// Kotlin
		patternsMap.put(LANGUAGE_KOTLIN, new Pattern[]{
			Pattern.compile("\\b(abstract|actual|annotation|as|as\\?|break|by|catch|class|companion|const|"
			+ "constructor|continue|crossinline|data|delegate|do|dynamic|else|enum|expect|external|false|"
			+ "field|final|finally|for|fun|get|if|import|in|infix|init|inline|inner|interface|internal|"
			+ "is|it|lateinit|noinline|null|object|open|operator|out|override|package|param|private|"
			+ "property|protected|public|receiver|reified|return|sealed|set|setparam|super|suspend|"
			+ "tailrec|this|throw|true|try|typealias|typeof|val|var|vararg|when|where|while|yield)\\b"),
			Pattern.compile("\\b([A-Z][a-zA-Z0-9_]*)\\b"),
			Pattern.compile("\"\"\"([\\s\\S]*?)\"\"\""),
			Pattern.compile("\"([^\"\\\\]|\\\\.)*\""),
			Pattern.compile("'([^'\\\\]|\\\\.)*'"),
			Pattern.compile("//.*?$", Pattern.MULTILINE),
			Pattern.compile("/\\*([\\s\\S]*?)\\*/"),
			Pattern.compile("\\b\\d+(\\.\\d+)?([eE][+-]?\\d+)?[fF]?\\b"),
			Pattern.compile("0[xX][0-9A-Fa-f]+"),
			Pattern.compile("0[bB][01]+"),
			Pattern.compile("[+\\-*/=<>!&|%^~?:]+"),
			Pattern.compile("[{}();,.\\[\\]]"),
			Pattern.compile("\\$[A-Za-z_][A-Za-z0-9_]*"),
			Pattern.compile("\\b(companion|object)\\s+\\w+")
		});
		
		colorsMap.put(LANGUAGE_KOTLIN, new int[]{
			Color.parseColor("#569CD6"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#4EC9B0")
		});
		
		boldMap.put(LANGUAGE_KOTLIN, new Boolean[]{
			true, false, false, false, false, false, false, false, false, false,
			false, false, false, false
		});
		
		// Swift
		patternsMap.put(LANGUAGE_SWIFT, new Pattern[]{
			Pattern.compile("\\b(associatedtype|class|deinit|enum|extension|fileprivate|func|import|init|"
			+ "inout|internal|let|open|operator|private|protocol|public|static|struct|subscript|typealias|"
			+ "var|break|case|continue|default|defer|do|else|fallthrough|for|guard|if|in|repeat|return|"
			+ "switch|where|while|as|Any|catch|false|is|nil|rethrows|throw|throws|true|try|associativity|"
			+ "convenience|dynamic|didSet|final|get|infix|indirect|lazy|left|mutating|none|nonmutating|"
			+ "optional|override|postfix|precedence|prefix|Protocol|required|right|set|some|Type|unowned|"
			+ "weak|willSet|#available|#colorLiteral|#column|#dsohandle|#else|#elseif|#endif|#error|#file|"
			+ "#fileID|#filePath|#function|#if|#imageLiteral|#keyPath|#line|#selector|#sourceLocation|#warning)\\b"),
			Pattern.compile("\\b([A-Z][a-zA-Z0-9_]*)\\b"),
			Pattern.compile("\"([^\"\\\\]|\\\\.)*\""),
			Pattern.compile("//.*?$", Pattern.MULTILINE),
			Pattern.compile("/\\*([\\s\\S]*?)\\*/"),
			Pattern.compile("\\b\\d+(\\.\\d+)?([eE][+-]?\\d+)?\\b"),
			Pattern.compile("0[xX][0-9A-Fa-f]+"),
			Pattern.compile("0[oO][0-7]+"),
			Pattern.compile("0[bB][01]+"),
			Pattern.compile("[+\\-*/=<>!&|%^~?:]+"),
			Pattern.compile("[{}();,.\\[\\]]"),
			Pattern.compile("\\$[0-9]+"),
			Pattern.compile("\\b(self|Self)\\b")
		});
		
		colorsMap.put(LANGUAGE_SWIFT, new int[]{
			Color.parseColor("#569CD6"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#4EC9B0")
		});
		
		boldMap.put(LANGUAGE_SWIFT, new Boolean[]{
			true, false, false, false, false, false, false, false, false, false,
			false, false, false
		});
		
		// Ruby
		patternsMap.put(LANGUAGE_RUBY, new Pattern[]{
			Pattern.compile("\\b(__ENCODING__|__LINE__|__FILE__|BEGIN|END|alias|and|begin|break|case|class|"
			+ "def|defined\\?|do|else|elsif|end|ensure|false|for|if|in|module|next|nil|not|or|redo|rescue|"
			+ "retry|return|self|super|then|true|undef|unless|until|when|while|yield|attr_accessor|"
			+ "attr_reader|attr_writer|include|extend|require|load|public|private|protected|raise|throw|"
			+ "catch|loop|proc|lambda|callcc|caller|eval|exec|exit|fork|gets|putc|puts|readline|readlines|"
			+ "select|test|trace_var|untrace_var|warn|Array|Hash|Integer|Float|String|Symbol|Time|Date|"
			+ "DateTime|File|Dir|Math|NilClass|TrueClass|FalseClass|Object|Module|Class|Struct|Proc|"
			+ "Lambda|Method|Range|Regexp|Set|Complex|Rational|BigDecimal|BigInt|Enumerator|Continuation|"
			+ "Thread|ThreadGroup|Mutex|ConditionVariable|Queue|SizedQueue|Monitor|Mixin|Observable|"
			+ "Singleton|Forwardable|DelegateClass|Benchmark|Logger|OptionParser|OpenStruct|Pathname|"
			+ "PrettyPrint|Prime|Profile|Profiler__|PStore|Racc|RDoc|REXML|RSS|Scanf|Shell|Singleton|"
			+ "Tempfile|Test|Tk|TSort|URI|Vector|YAML|Zlib)\\b"),
			Pattern.compile(":[A-Za-z_][A-Za-z0-9_]*"),
			Pattern.compile("@[A-Za-z_][A-Za-z0-9_]*"),
			Pattern.compile("@@[A-Za-z_][A-Za-z0-9_]*"),
			Pattern.compile("\\$[A-Za-z_][A-Za-z0-9_]*"),
			Pattern.compile("\"([^\"\\\\]|\\\\.)*\""),
			Pattern.compile("'([^'\\\\]|\\\\.)*'"),
			Pattern.compile("`([^`\\\\]|\\\\.)*`"),
			Pattern.compile("%[qQrRsSwWx]?\\([^)]*\\)|%[qQrRsSwWx]?\\[[^]]*\\]|%[qQrRsSwWx]?\\{[^}]*\\}|%[qQrRsSwWx]?<[^>]*>|%[qQrRsSwWx]?\\|[^|]*\\|"),
			Pattern.compile("#.*?$", Pattern.MULTILINE),
			Pattern.compile("=begin([\\s\\S]*?)=end"),
			Pattern.compile("\\b\\d+(\\.\\d+)?\\b"),
			Pattern.compile("[+\\-*/=<>!&|%^~?:]+"),
			Pattern.compile("[{}();,.\\[\\]]")
		});
		
		colorsMap.put(LANGUAGE_RUBY, new int[]{
			Color.parseColor("#569CD6"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#D4D4D4")
		});
		
		boldMap.put(LANGUAGE_RUBY, new Boolean[]{
			true, false, false, false, false, false, false, false, false, false,
			false, false, false, false
		});
		
		// Go
		patternsMap.put(LANGUAGE_GO, new Pattern[]{
			Pattern.compile("\\b(break|case|chan|const|continue|default|defer|else|fallthrough|for|func|"
			+ "go|goto|if|import|interface|map|package|range|return|select|struct|switch|type|var|"
			+ "bool|byte|complex64|complex128|error|float32|float64|int8|int16|int32|int64|string|"
			+ "uint8|uint16|uint32|uint64|int|uint|uintptr|rune|true|false|iota|nil|append|cap|close|"
			+ "complex|copy|delete|imag|len|make|new|panic|print|println|real|recover|close|closed|"
			+ "chan|func|interface|map|struct|type|var|package|import|go|defer|fallthrough|range|"
			+ "select|switch|case|default|if|else|for|goto|break|continue|return|const|var)\\b"),
			Pattern.compile("\\b([A-Z][a-zA-Z0-9_]*)\\b"),
			Pattern.compile("\"([^\"\\\\]|\\\\.)*\""),
			Pattern.compile("`([^`]*)`"),
			Pattern.compile("'([^'\\\\]|\\\\.)*'"),
			Pattern.compile("//.*?$", Pattern.MULTILINE),
			Pattern.compile("/\\*([\\s\\S]*?)\\*/"),
			Pattern.compile("\\b\\d+(\\.\\d+)?([eE][+-]?\\d+)?\\b"),
			Pattern.compile("0[xX][0-9A-Fa-f]+"),
			Pattern.compile("0[oO][0-7]+"),
			Pattern.compile("0[bB][01]+"),
			Pattern.compile("[+\\-*/=<>!&|%^~?:]+"),
			Pattern.compile("[{}();,.\\[\\]]"),
			Pattern.compile("\\$(\\w+|\\{[^}]*\\})")
		});
		
		colorsMap.put(LANGUAGE_GO, new int[]{
			Color.parseColor("#569CD6"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#4EC9B0")
		});
		
		boldMap.put(LANGUAGE_GO, new Boolean[]{
			true, false, false, false, false, false, false, false, false, false,
			false, false, false, false
		});
		
		// Rust
		patternsMap.put(LANGUAGE_RUST, new Pattern[]{
			Pattern.compile("\\b(as|break|const|continue|crate|else|enum|extern|false|fn|for|if|impl|in|"
			+ "let|loop|match|mod|move|mut|pub|ref|return|self|Self|static|struct|super|trait|true|type|"
			+ "unsafe|use|where|while|async|await|dyn|abstract|become|box|do|final|macro|override|priv|"
			+ "try|typeof|unsized|virtual|yield|union|'static|bool|char|f32|f64|i8|i16|i32|i64|i128|isize|"
			+ "str|u8|u16|u32|u64|u128|usize|Option|Result|String|Vec|Box|Rc|Arc|Cell|RefCell|HashMap|"
			+ "HashSet|BTreeMap|BTreeSet|BinaryHeap|VecDeque|LinkedList|Iterator|IntoIterator|FromIterator|"
			+ "Extend|Default|Clone|Copy|Debug|PartialEq|Eq|PartialOrd|Ord|Hash|Send|Sync|Sized|Drop|Fn|"
			+ "FnMut|FnOnce|ToOwned|Borrow|BorrowMut|AsRef|AsMut|Into|From|TryInto|TryFrom|ToString|"
			+ "CString|CStr|OsString|OsStr|PathBuf|Path|Box|Rc|Arc|Weak|Mutex|RwLock|Condvar|Barrier|"
			+ "Once|Lazy|AtomicBool|AtomicIsize|AtomicUsize|AtomicI8|AtomicI16|AtomicI32|AtomicI64|"
			+ "AtomicU8|AtomicU16|AtomicU32|AtomicU64|Duration|Instant|SystemTime|File|Dir|DirEntry|"
			+ "Read|Write|Seek|BufRead|Error|Result|Ok|Err|Some|None|println!|print!|eprintln!|eprint!|"
			+ "format!|write!|writeln!|panic!|assert!|assert_eq!|assert_ne!|unreachable!|unimplemented!|"
			+ "macro_rules!|vec!|include!|include_str!|include_bytes!|cfg!|env!|option_env!|concat!|"
			+ "concat_idents!|line!|column!|file!|stringify!|module_path!)\\b"),
			Pattern.compile("\\b([A-Z][a-zA-Z0-9_]*)\\b"),
			Pattern.compile("\"([^\"\\\\]|\\\\.)*\""),
			Pattern.compile("'([^'\\\\]|\\\\.)*'"),
			Pattern.compile("r#\"([\\s\\S]*?)\"#"),
			Pattern.compile("//.*?$", Pattern.MULTILINE),
			Pattern.compile("//!.*?$", Pattern.MULTILINE),
			Pattern.compile("/\\*([\\s\\S]*?)\\*/"),
			Pattern.compile("/\\*\\*([\\s\\S]*?)\\*/"),
			Pattern.compile("\\b\\d+(\\.\\d+)?([eE][+-]?\\d+)?[fF]?\\b"),
			Pattern.compile("0[xX][0-9A-Fa-f_]+"),
			Pattern.compile("0[oO][0-7_]+"),
			Pattern.compile("0[bB][01_]+"),
			Pattern.compile("[+\\-*/=<>!&|%^~?:@]+"),
			Pattern.compile("[{}();,.\\[\\]]"),
			Pattern.compile("'[a-zA-Z_][a-zA-Z0-9_]*")
		});
		
		colorsMap.put(LANGUAGE_RUST, new int[]{
			Color.parseColor("#569CD6"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#4EC9B0")
		});
		
		boldMap.put(LANGUAGE_RUST, new Boolean[]{
			true, false, false, false, false, false, false, false, false, false,
			false, false, false, false, false, false
		});
		
		// Markdown
		patternsMap.put(LANGUAGE_MARKDOWN, new Pattern[]{
			Pattern.compile("^#{1,6}\\s.*$", Pattern.MULTILINE),
			Pattern.compile("\\*\\*[^*]+\\*\\*|__[^_]+__"),
			Pattern.compile("\\*[^*]+\\*|_[^_]+_"),
			Pattern.compile("`[^`]+`"),
			Pattern.compile("```[\\s\\S]*?```|~~~[\\s\\S]*?~~~"),
			Pattern.compile("\\[([^\\]]+)\\]\\(([^\\)]+)\\)"),
			Pattern.compile("!\\[([^\\]]+)\\]\\(([^\\)]+)\\)"),
			Pattern.compile("^\\s*[-*+]\\s.*$", Pattern.MULTILINE),
			Pattern.compile("^\\s*\\d+\\.\\s.*$", Pattern.MULTILINE),
			Pattern.compile("^>.*$", Pattern.MULTILINE),
			Pattern.compile("\\|.*\\|"),
			Pattern.compile("^---+|^\\*\\*\\*+|^___+", Pattern.MULTILINE),
			Pattern.compile("\\b_{2}[a-zA-Z0-9_]+_{2}\\b"),
			Pattern.compile("\\b\\*{2}[a-zA-Z0-9_]+\\*{2}\\b")
		});
		
		colorsMap.put(LANGUAGE_MARKDOWN, new int[]{
			Color.parseColor("#569CD6"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#9CDCFE"),
			Color.parseColor("#9CDCFE"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#D4D4D4")
		});
		
		boldMap.put(LANGUAGE_MARKDOWN, new Boolean[]{
			true, true, false, false, false, false, false, false, false, false,
			false, false, true, true
		});
		
		// YAML
		patternsMap.put(LANGUAGE_YAML, new Pattern[]{
			Pattern.compile("^\\s*[A-Za-z_][A-Za-z0-9_]*\\s*:"),
			Pattern.compile("&[A-Za-z_][A-Za-z0-9_]*"),
			Pattern.compile("\\*[A-Za-z_][A-Za-z0-9_]*"),
			Pattern.compile("!![A-Za-z_][A-Za-z0-9_]*"),
			Pattern.compile("\"([^\"\\\\]|\\\\.)*\""),
			Pattern.compile("'([^'\\\\]|\\\\.)*'"),
			Pattern.compile("#.*?$", Pattern.MULTILINE),
			Pattern.compile("\\b(true|false|null|yes|no|on|off)\\b"),
			Pattern.compile("\\b\\d+(\\.\\d+)?([eE][+-]?\\d+)?\\b"),
			Pattern.compile("^---|^\\.\\.\\.", Pattern.MULTILINE),
			Pattern.compile("^\\s*-\\s"),
			Pattern.compile("\\|\\>|-\\|\\>|\\+\\|\\>"),
			Pattern.compile("\\b(0[xX][0-9A-Fa-f]+|0[oO][0-7]+|0[bB][01]+)\\b"),
			Pattern.compile("\\b(inf|nan|INF|NAN|Inf|NaN)\\b")
		});
		
		colorsMap.put(LANGUAGE_YAML, new int[]{
			Color.parseColor("#9CDCFE"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#569CD6"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#C586C0"),
			Color.parseColor("#C586C0"),
			Color.parseColor("#C586C0"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#B5CEA8")
		});
		
		boldMap.put(LANGUAGE_YAML, new Boolean[]{
			false, false, false, false, false, false, false, false, false, false,
			false, false, false, false
		});
		
		// Shell
		patternsMap.put(LANGUAGE_SHELL, new Pattern[]{
			Pattern.compile("\\b(if|then|else|elif|fi|case|esac|for|in|while|do|done|until|select|function|"
			+ "time|export|readonly|declare|typeset|local|unset|unsetenv|set|setenv|shift|eval|exec|"
			+ "exit|return|break|continue|trap|cd|pwd|pushd|popd|dirs|let|test|read|echo|printf|"
			+ "alias|unalias|source|dot|bg|fg|jobs|kill|wait|suspend|hash|bind|builtin|caller|command|"
			+ "compgen|complete|compopt|coproc|dirs|disown|enable|fc|fg|getopts|help|history|jobs|"
			+ "kill|logout|mapfile|popd|printf|pushd|readarray|readonly|select|set|shift|shopt|suspend|"
			+ "times|trap|type|typeset|ulimit|umask|unalias|unset|wait|true|false|null|yes|no|"
			+ "awk|bash|cat|cc|cd|chmod|cp|date|dd|diff|echo|ed|emacs|expr|fgrep|find|grep|head|"
			+ "kill|ln|ls|mail|make|mkdir|more|mv|nohup|od|paste|pr|ps|pwd|rm|rmdir|sed|sh|sort|"
			+ "stty|tail|tar|touch|tr|umask|uniq|uudecode|uuencode|vi|wc|who|write)\\b"),
			Pattern.compile("\\$[A-Za-z_][A-Za-z0-9_]*"),
			Pattern.compile("\\$\\{[^}]*\\}"),
			Pattern.compile("\\$\\([^)]*\\)"),
			Pattern.compile("\"([^\"\\\\]|\\\\.)*\""),
			Pattern.compile("'([^'\\\\]|\\\\.)*'"),
			Pattern.compile("`([^`\\\\]|\\\\.)*`"),
			Pattern.compile("#.*?$", Pattern.MULTILINE),
			Pattern.compile("\\b\\d+\\b"),
			Pattern.compile("[|&;<>]+"),
			Pattern.compile("\\b(0[xX][0-9A-Fa-f]+|0[oO][0-7]+|0[bB][01]+)\\b"),
			Pattern.compile("\\b([A-Z_][A-Z0-9_]*)\\b"),
			Pattern.compile("^\\s*:\\s*$", Pattern.MULTILINE)
		});
		
		colorsMap.put(LANGUAGE_SHELL, new int[]{
			Color.parseColor("#569CD6"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#C586C0")
		});
		
		boldMap.put(LANGUAGE_SHELL, new Boolean[]{
			true, false, false, false, false, false, false, false, false, false,
			false, false, false
		});
		
		// Lua
		patternsMap.put(LANGUAGE_LUA, new Pattern[]{
			Pattern.compile("\\b(and|break|do|else|elseif|end|false|for|function|goto|if|in|local|nil|not|"
			+ "or|repeat|return|then|true|until|while|self|require|package|module|import|export|"
			+ "assert|collectgarbage|dofile|error|getfenv|getmetatable|ipairs|load|loadfile|loadstring|"
			+ "next|pairs|pcall|print|rawequal|rawget|rawlen|rawset|select|setfenv|setmetatable|tonumber|"
			+ "tostring|type|unpack|xpcall|coroutine|debug|io|math|os|string|table|bit32|utf8|_G|_VERSION)\\b"),
			Pattern.compile("\\b([A-Z][a-zA-Z0-9_]*)\\b"),
			Pattern.compile("\"([^\"\\\\]|\\\\.)*\""),
			Pattern.compile("'([^'\\\\]|\\\\.)*'"),
			Pattern.compile("\\[\\[([\\s\\S]*?)\\]\\]"),
			Pattern.compile("--.*?$", Pattern.MULTILINE),
			Pattern.compile("--\\[\\[([\\s\\S]*?)\\]\\]"),
			Pattern.compile("\\b\\d+(\\.\\d+)?([eE][+-]?\\d+)?\\b"),
			Pattern.compile("0[xX][0-9A-Fa-f]+"),
			Pattern.compile("[+\\-*/=<>!&|%^~?:#]+"),
			Pattern.compile("[{}();,.\\[\\]]"),
			Pattern.compile("\\.\\.\\.|\\.\\."),
			Pattern.compile(":\\s*[a-zA-Z_][a-zA-Z0-9_]*")
		});
		
		colorsMap.put(LANGUAGE_LUA, new int[]{
			Color.parseColor("#569CD6"),
			Color.parseColor("#4EC9B0"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#D69D85"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#6A9955"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#B5CEA8"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#D4D4D4"),
			Color.parseColor("#4EC9B0")
		});
		
		boldMap.put(LANGUAGE_LUA, new Boolean[]{
			true, false, false, false, false, false, false, false, false, false,
			false, false, false
		});
	}
	
	public static void setup(EditText editText, int language) {
		if (patternsMap.containsKey(language)) {
			editText.addTextChangedListener(new GenericTextWatcher(
			editText, 
			patternsMap.get(language), 
			colorsMap.get(language),
			boldMap.get(language)
			));
		} else {
			// Default to plain text if language not supported
			editText.addTextChangedListener(new GenericTextWatcher(
			editText, 
			new Pattern[0], 
			new int[0],
			new Boolean[0]
			));
		}
	}
	
	public static int detectLanguageFromExtension(String filename) {
		if (filename == null) return -1;
		
		String ext = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
		
		switch (ext) {
			case "xml": return LANGUAGE_XML;
			case "json": return LANGUAGE_JSON;
			case "php": return LANGUAGE_PHP;
			case "java": return LANGUAGE_JAVA;
			case "js": return LANGUAGE_JAVASCRIPT;
			case "html": case "htm": return LANGUAGE_HTML;
			case "css": return LANGUAGE_CSS;
			case "py": return LANGUAGE_PYTHON;
			case "cpp": case "cc": case "cxx": case "h": case "hpp": return LANGUAGE_CPP;
			case "cs": return LANGUAGE_CSHARP;
			case "sql": return LANGUAGE_SQL;
			case "kt": return LANGUAGE_KOTLIN;
			case "swift": return LANGUAGE_SWIFT;
			case "rb": return LANGUAGE_RUBY;
			case "go": return LANGUAGE_GO;
			case "rs": return LANGUAGE_RUST;
			case "md": return LANGUAGE_MARKDOWN;
			case "yml": case "yaml": return LANGUAGE_YAML;
			case "sh": case "bash": return LANGUAGE_SHELL;
			case "lua": return LANGUAGE_LUA;
			default: return -1;
		}
	}
	
	public static class GenericTextWatcher implements TextWatcher {
		private final EditText editText;
		private final Pattern[] patterns;
		private final int[] colors;
		private final Boolean[] boldFlags;
		private String lastText = "";
		
		GenericTextWatcher(EditText editText, Pattern[] patterns, int[] colors, Boolean[] boldFlags) {
			this.editText = editText;
			this.patterns = patterns;
			this.colors = colors;
			this.boldFlags = boldFlags;
		}
		
		@Override 
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			// Optional: implement undo functionality if needed
		}
		
		@Override 
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
		
		@Override 
		public void afterTextChanged(Editable s) {
			String currentText = s.toString();
			if (currentText.equals(lastText)) return;
			lastText = currentText;
			
			int selStart = editText.getSelectionStart();
			int selEnd = editText.getSelectionEnd();
			
			// Remove existing spans
			ForegroundColorSpan[] colorSpans = s.getSpans(0, s.length(), ForegroundColorSpan.class);
			for (ForegroundColorSpan span : colorSpans) s.removeSpan(span);
			
			StyleSpan[] styleSpans = s.getSpans(0, s.length(), StyleSpan.class);
			for (StyleSpan span : styleSpans) s.removeSpan(span);
			
			// Apply new highlighting
			for (int i = 0; i < patterns.length; i++) {
				Matcher m = patterns[i].matcher(s);
				while (m.find()) {
					s.setSpan(
					new ForegroundColorSpan(colors[i]),
					m.start(),
					m.end(),
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
					);
					
					if (boldFlags[i]) {
						s.setSpan(
						new StyleSpan(Typeface.BOLD),
						m.start(),
						m.end(),
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
						);
					}
				}
			}
			
			// Restore cursor position
			if (selStart <= s.length() && selEnd <= s.length()) {
				editText.setSelection(selStart, selEnd);
			}
		}
	}
	
	public static void highlightNow(EditText editText) {
		if (editText == null) return;
		// Trigger ulang afterTextChanged
		Editable text = editText.getText();
		if (text != null) {
			editText.setText(text.toString()); // memicu TextWatcher
			editText.setSelection(text.length()); // cursor tetap di akhir
		}
	}
}
